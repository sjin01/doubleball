package com.guanshj.framework.util.pagination;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PagePlugin implements Interceptor {

	private String dialect = "oracle";	//数据库方言
	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	private static String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)
	
	public Object intercept(Invocation ivk) throws Throwable {
		if(ivk.getTarget() instanceof RoutingStatementHandler){
			RoutingStatementHandler statementHandler = (RoutingStatementHandler)ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
			
			//if(mappedStatement.getId().matches(pageSqlId)){ //拦截需要分页的SQL
			if(mappedStatement.getId().endsWith("_page")){ //拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if(parameterObject==null){
					throw new NullPointerException("parameterObject尚未实例化！");
				}else{
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					String countSql = "select count(0) from (" + sql+ ") tmp_count"; //记录统计
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					//BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql,boundSql.getParameterMappings(),parameterObject);
					
					//这里，使用boundSql来设置countBS的参数，目的是boundSql里面包含了扩展参数，而countBS里面没有，
					//会导致foreach这样的处理赋值失败
					setParameters(countStmt,mappedStatement,boundSql,parameterObject);					
//					setParameters(countStmt,mappedStatement,countBS,parameterObject);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					//System.out.println(count);
					Page page = null;
					if(parameterObject instanceof Page){	//参数就是Page实体
						 page = (Page) parameterObject;
						 page.setEntityOrField(true);	 //见com.flf.entity.Page.entityOrField 注释
						page.setTotalResult(count);
					}else if( parameterObject instanceof Map ){
						page = (Page) ((Map)parameterObject).get("page");
						page.setTotalResult(count);
					}else{	//参数为某个实体，该实体拥有Page属性
						Field pageField = ReflectHelper.getFieldByFieldName(parameterObject,"page");
						if(pageField!=null){
							page = (Page) ReflectHelper.getValueByFieldName(parameterObject,"page");
							if(page==null)
								page = new Page();
							page.setEntityOrField(false); //见com.flf.entity.Page.entityOrField 注释
							page.setTotalResult(count);
							ReflectHelper.setValueByFieldName(parameterObject,"page", page); //通过反射，对实体对象设置分页对象
						}else{
							throw new NoSuchFieldException(parameterObject.getClass().getName()+"不存在 page 属性！");
						}
					}
					String pageSql = generatePageSql(sql,page);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	
	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws java.sql.SQLException
	 */
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}
	
	/**
	 * 根据数据库方言，生成特定的分页sql
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql, Page page) {
		if ((page != null) && (!"".equals(this.dialect))) {
			StringBuffer pageSql = new StringBuffer();

			if ("mysql".equals(this.dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + page.getCurrentResult() + ","
						+ page.getShowCount());
			} else if ("oracle".equals(this.dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");

				int lastOrderbyPos = sql.toLowerCase().lastIndexOf("order by");
				// 20130508 zhangwg 解决sql中出现group by而报错的bug
				int lastGroupbyPos = sql.toLowerCase().lastIndexOf("group by");
				int lastDistinct = sql.toLowerCase().lastIndexOf("distinct");
				
				if (lastOrderbyPos == -1) {
					// sql 不以order by 结尾，分两种情况：1  以 group by 结尾 2 不以group by 结尾
					if(lastGroupbyPos>-1) {//1  以 group by 
						/*
						pageSql.append(
								sql.substring(0, lastGroupbyPos + 9)
										+ " rownum,"
										+ sql.substring(lastGroupbyPos + 9,
												sql.length()));*/
						pageSql.append(sql);
					} else {//2 不以group by 结尾
						 pageSql.append(sql).append(" order by rownum");	
					}
				} else {
					if (lastGroupbyPos > -1) {
						/*
						pageSql.append(
								sql.substring(0, lastGroupbyPos + 9)
										+ " rownum,"
										+ sql.substring(lastGroupbyPos + 9,
												sql.length()));*/
						pageSql.append(sql);
					} else if (lastGroupbyPos == -1) {
						int lastBracketsPos1 = sql.lastIndexOf("(");
						int lastBracketsPos2 = sql.indexOf(")",
								lastBracketsPos1);
						int lastSelect = sql.toLowerCase().lastIndexOf(
								"select ");

						if ((lastBracketsPos1 > -1) && (lastBracketsPos2 > -1)
								&& (lastSelect > lastBracketsPos1)
								&& (lastSelect < lastBracketsPos2)) {
							if ((lastOrderbyPos > lastBracketsPos1)
									&& (lastOrderbyPos < lastBracketsPos2)) {
								pageSql.append(sql).append(" order by rownum");
							} else if (lastOrderbyPos > lastBracketsPos2)
								pageSql.append(sql).append(",rownum");
							else {
								pageSql.append(sql).append(" order by rownum");
							}
						} else {
							/* 20130516 解决order by 后面带 asc 或 desc 出来 拼接后的sql形如 id desc,rownum 的错误。 
							 */
							pageSql.append(sql);
						}
					}
				}
				
				
				pageSql.append(") tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult() + page.getShowCount());
				pageSql.append(" order by ROWNUM ");
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());
			} else if ("db2".equals(this.dialect)) {
				pageSql.append("select * from (select B.*, ROWNUMBER() OVER() ");
				pageSql.append("AS RN from (").append(sql).append(") AS B)AS ");
				pageSql.append("A where A.RN BETWEEN ")
						.append(page.getCurrentResult()).append(" AND ");

				pageSql.append(page.getCurrentResult() + page.getShowCount());
			}

			return pageSql.toString();
		}
		return sql;
	}
	
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if ("".equals(dialect)){//Tools.isEmpty(dialect)) {
			System.out.println("dialect property is not found!");
		}
		pageSqlId = p.getProperty("pageSqlId");
		if ("".equals(pageSqlId)){//Tools.isEmpty(pageSqlId)) {
			System.out.println("pageSqlId property is not found!");
		}
	}
	
	public static void main(String[] args) {
		PagePlugin plugin = new PagePlugin();
		plugin.dialect = "oracle";
		Page page = new Page();
		page.setCurrentPage(1);
		page.setShowCount(10);
		//System.out.println(plugin.generatePageSql(" select id from ac_role group by id ", page));
//		System.out.println(plugin.generatePageSql(" select id from ac_role t group by  rownum,id having id > 1",page));
		System.out.println(plugin.generatePageSql("select * from ( select * from ac_app t)   ",page));
		System.out.println(plugin.generatePageSql("select * from ac_app t   ",page));
		System.out.println(plugin.generatePageSql("select * from ac_app t  ORDER BY ID ASC ",page));
		System.out.println(plugin.generatePageSql("select distinct t.id from ac_app t  ORDER BY ID ASC ",page));
	}
}
