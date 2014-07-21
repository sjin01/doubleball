package com.guanshj.services;

import com.guanshj.model.DoubleBall;
import com.guanshj.vo.DoubleBallDto;

import java.util.List;

/**
 * 双色球  服务接口
 * 创建日期: 14-7-9  上午11:09
 *
 * @author: guanshj QQ: 928990049
 */
public interface DoubleBallService {

    int deleteByid(Integer id) throws Exception;

    int deleteByEntity(DoubleBall record) throws Exception;

    int insert(DoubleBall record) throws Exception;

    int update(DoubleBall record) throws Exception;

    DoubleBall selectByid(Integer id) throws Exception;

    List<DoubleBall> selectByEntity(DoubleBall record) throws Exception;

    int insertList(List<DoubleBall> list) throws Exception;

    /**
     * 分页
     *
     * @param record
     * @return
     */
    List<DoubleBall> selectByEntityPaging(DoubleBall record) throws Exception;

    /**
     * 查询分页表格视图  红球横向展示
     * @param record
     * @return
     * @throws Exception
     */
    List<DoubleBallDto> selectTableDataViewPage (DoubleBall record) throws Exception;

    /**
     * @param period   期号
     * @param redBall1 红球1-6
     * @param redBall2
     * @param redBall3
     * @param redBall4
     * @param redBall5
     * @param redBall6
     * @param blueBall 蓝球
     * @throws Exception
     */
    void saveDoubleBallRecord(Integer period, Integer redBall1, Integer redBall2, Integer redBall3,
                              Integer redBall4, Integer redBall5, Integer redBall6, Integer blueBall ,String flag)
            throws Exception;


    /**********************
     * ##############  分析
     * ####################################################################################
     */


    /**
     * 概览统计 ------ 分组、并按出现次数排序
     * @param type
     * @return
     * @throws Exception
     */
    List<DoubleBall> selectValueOrderByCount (Integer type) throws Exception;
}
