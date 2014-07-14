package com.guanshj.framework.util.pagination;

import java.util.List;
import java.util.Map;

/**
 * Title :盛通-- 教育信息服务平台
 * Description:
 * 创建日期: 14-7-14  上午11:11
 *
 * @author: guanshj QQ: 928990049
 */
public class Pagination4Datatable {
    /**
     * 根据分页对象生成jQuery dataTable需要的分页对象
     *
     * @param pagination 系统原始分页对象
     * @param sEcho      jQuery dataTable分页对象需要的属性
     * @return Pagination4Datatable
     */
    public static Pagination4Datatable getInstance(Paging pagination, String sEcho) {
        return new Pagination4Datatable(pagination, sEcho);
    }

    /**
     * 根据数据集，总行数生成分页对象
     *
     * @param list       数据集
     * @param totalCount 总行数
     * @param sEcho
     * @return Pagination4Datatable
     */
    public static Pagination4Datatable getInstance(List list, Integer totalCount, String sEcho) {
        Paging paging = new Paging();
        paging.setList(list);
        paging.setRowCount(totalCount);

        return new Pagination4Datatable(paging, sEcho);
    }

    /**
     * 根据分页对象生成jQuery dataTable需要的分页对象
     *
     * @param pagination 系统原始分页对象
     * @param sEcho      jQuery dataTable分页对象需要的属性
     * @param sColumns   jQuery dataTable指定需要重新排序的列名字符串
     * @return Pagination4Datatable
     */
    public static Pagination4Datatable getInstance(Paging pagination, String sEcho, String sColumns) {
        return new Pagination4Datatable(pagination, sEcho, sColumns);
    }

    /**
     * 无参构造方法
     */
    private Pagination4Datatable() {

    }

    /**
     * 构造方法
     *
     * @param pagination 　默认分页对象
     * @param sEcho      jQuery dataTable需要的字符串
     */
    private Pagination4Datatable(Paging pagination, String sEcho) {
        this.setsEcho(sEcho);
        this.setiTotalRecords(pagination.getRowCount());
        this.setiTotalDisplayRecords(pagination.getRowCount());
        this.setAaData(pagination.getList());
        this.setMyprop(pagination.getMyprop());
    }


    /**
     * 构造方法
     *
     * @param pagination 默认分页对象
     * @param sEcho      jQuery dataTable需要的字符串
     * @param sColumns   重新排序的列名字符串
     */
    private Pagination4Datatable(Paging pagination, String sEcho, String sColumns) {
        this.setsEcho(sEcho);
        this.setiTotalRecords(pagination.getRowCount());
        this.setiTotalDisplayRecords(pagination.getRowCount());
        this.setAaData(pagination.getList());
        this.setsColumns(sColumns);
        this.setMyprop(pagination.getMyprop());
    }


    /**
     * 未过滤的记录总数
     */
    private int iTotalRecords;

    /**
     * 过滤的记录总数
     */
    private int iTotalDisplayRecords;

    /**
     * @return the iTotalRecords
     */
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    /**
     * jQuery dataTable 特殊字符
     */
    private String sEcho;

    /**
     * 多个列名字符串，用逗号分隔
     */
    private String sColumns;


    /**
     * 查询到的记录数组
     */
    private List aaData;


    /**
     * @param iTotalRecords the iTotalRecords to set
     */
    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }


    /**
     * @return the iTotalDisplayRecords
     */
    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }


    /**
     * @param iTotalDisplayRecords the iTotalDisplayRecords to set
     */
    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }


    /**
     * @return the sEcho
     */
    public String getsEcho() {
        return sEcho;
    }


    /**
     * @param sEcho the sEcho to set
     */
    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }


    /**
     * @return the sColumns
     */
    public String getsColumns() {
        return sColumns;
    }


    /**
     * @param sColumns the sColumns to set
     */
    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }


    /**
     * @return the aaData
     */
    public List getAaData() {
        return aaData;
    }


    /**
     * @param aaData the aaData to set
     */
    public void setAaData(List aaData) {
        this.aaData = aaData;
    }

    /**
     * 附加统计属性3
     */
    private Map<String, Object> myprop;

    public Map<String, Object> getMyprop() {
        return myprop;
    }

    public void setMyprop(Map<String, Object> myprop) {
        this.myprop = myprop;
    }
}
