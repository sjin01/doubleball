package com.guanshj.framework.util.pagination;

import java.io.Serializable;

public class Page implements Serializable{
	private int showCount = 10; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage = 1;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();

	private String sortName;
	private String sortOrder;
	
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("	<ul>\n");
			if(currentPage==1){
				sb.append("	<li class=\"pageinfo\">首页</li>\n");
				sb.append("	<li class=\"pageinfo\">上页</li>\n");
			}else{	
				sb.append("	<li><a href=\"javascript:scroll(0,0)\" mce_href=\"javascript:scroll(0,0)\" onclick=\"nPage(1)\">首页</a></li>\n");
				sb.append("	<li><a href=\"javascript:scroll(0,0)\" mce_href=\"javascript:scroll(0,0)\" onclick=\"nPage("+(currentPage-1)+")\">上页</a></li>\n");
			}
			int showTag = 3;	//分页标签显示数量
			
			int startTag = 1;
			int endTag = totalPage;
			
			if(totalPage > showTag){
				if(currentPage == 1){
					startTag = 1;
					endTag = showTag;
				}else if(currentPage == totalPage){
					startTag = currentPage - showTag + 1;
					endTag = currentPage;
				}else{
					startTag = currentPage - 1;
					endTag = currentPage + showTag - 2;
				}
			}
			
			for(int i=startTag; i<=endTag; i++){
				
				if(currentPage==i)
				{
					sb.append("<li class=\"current\">"+i+"</li>\n");
				}
				else
				{
					sb.append("	<li><a href=\"javascript:scroll(0,0)\" mce_href=\"javascript:scroll(0,0)\" onclick=\"nPage("+i+")\">"+i+"</a></li>\n");
				}
			}
			if(currentPage==totalPage){
				sb.append("	<li class=\"pageinfo\">下页</li>\n");
				sb.append("	<li class=\"pageinfo\">尾页</li>\n");
			}else{
				sb.append("	<li><a href=\"javascript:scroll(0,0)\" mce_href=\"javascript:scroll(0,0)\" onclick=\"nPage("+(currentPage+1)+")\">下页</a></li>\n");
				sb.append("	<li><a href=\"javascript:scroll(0,0)\" mce_href=\"javascript:scroll(0,0)\" onclick=\"nPage("+totalPage+")\">尾页</a></li>\n");
			}
			sb.append("	<li class=\"pageinfo\">第"+currentPage+"页</li>\n");
			sb.append("	<li class=\"pageinfo\">共"+totalPage+"页</li>\n");
			sb.append("</ul>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		if( showCount > 0 ){
			this.showCount = showCount;
		}else{
			System.out.println("error: can't set Page.showCount to 0 ");
		}
	}
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public boolean isEntityOrField() {
		return entityOrField;
	}
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
}