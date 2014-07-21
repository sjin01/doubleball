package com.guanshj.model;

import com.guanshj.framework.util.pagination.AdvancedPage;

import java.io.Serializable;

/**
 * Description:   双色球  实体类
 * 创建日期: 14-7-8  下午4:28
 *
 * @author: guanshj QQ: 928990049
 */
public class DoubleBall extends AdvancedPage implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer period;  //开奖期号
    private Integer value;   //开奖球号
    private Integer type;    //1红球；2蓝球

    private Integer count ; // 出现次数

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
