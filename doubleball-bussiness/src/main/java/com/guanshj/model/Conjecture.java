package com.guanshj.model;

import com.guanshj.framework.util.pagination.AdvancedPage;
import com.guanshj.framework.util.pagination.Page;

import java.io.Serializable;

/**
 * 我的 推测 实体类
 * Created with IntelliJ IDEA.
 * User: sjin
 * Date: 14-7-30
 * Time: 下午6:46
 * To change this template use File | Settings | File Templates.
 */
public class Conjecture extends Page implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer period;  //开奖期号
    private Integer value;   //开奖球号
    private Integer type;    //1红球；2蓝球

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
