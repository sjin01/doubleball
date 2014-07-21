package com.guanshj.services.impl;

import com.guanshj.constant.DoubleBallConstant;
import com.guanshj.enums.BallType;
import com.guanshj.model.DoubleBall;
import com.guanshj.services.DoubleBallService;
import com.guanshj.vo.DoubleBallDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 双色球 服务接口实现类
 * 创建日期: 14-7-9  上午11:10
 *
 * @author: guanshj QQ: 928990049
 */
@Service("doubleBallService")
public class DoubleBallServiceImpl extends BaseServiceImpl implements DoubleBallService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int deleteByid(Integer id) throws Exception {
        return sqlSessionTemplate.delete("DoubleBall.deleteByPrimaryKey",id);
    }

    @Override
    public int deleteByEntity(DoubleBall record) throws Exception {
        return sqlSessionTemplate.delete("DoubleBall.deleteByEntity",record);
    }

    @Override
    public int insert(DoubleBall record) throws Exception {
        return sqlSessionTemplate.insert("DoubleBall.insert" ,record);
    }

    @Override
    public int update(DoubleBall record) throws Exception {
        return sqlSessionTemplate.update("DoubleBall.updateByid" ,record);
    }

    @Override
    public DoubleBall selectByid(Integer id) throws Exception {
        return (DoubleBall) sqlSessionTemplate.selectOne("DoubleBall.selectByPrimaryKey" ,id);
    }

    @Override
    public List<DoubleBall> selectByEntity(DoubleBall record) throws Exception {
        return (List<DoubleBall>) sqlSessionTemplate.selectList("DoubleBall.selectByEntity" , record);
    }

    @Override
    public int insertList(List<DoubleBall> list) throws Exception {
        return sqlSessionTemplate.insert("DoubleBall.insertList" ,list);
    }

    @Override
    public List<DoubleBall> selectByEntityPaging(DoubleBall record) throws Exception {
        return (List<DoubleBall>) sqlSessionTemplate.selectList("DoubleBall.selectByEntityPaging_page" , record);
    }

    @Override
    public List<DoubleBallDto> selectTableDataViewPage(DoubleBall record) throws Exception {
        return (List<DoubleBallDto>) sqlSessionTemplate.selectList("DoubleBall.selectTableDataView_page" , record);
    }

    @Override
    public void saveDoubleBallRecord(Integer period, Integer redBall1, Integer redBall2,
                                     Integer redBall3, Integer redBall4, Integer redBall5,
                                     Integer redBall6, Integer blueBall ,String flag) throws Exception {
        // step1 : validate data
        if (period == null || redBall1 == null || redBall2 == null || redBall3 == null || redBall4 == null || redBall5 == null || redBall6 == null || blueBall == null) {
            System.out.println("参数不正确");
            throw new Exception();
        }

        if(flag.equals(DoubleBallConstant.FLAG_UPDATE)){
            // 清理数据
            DoubleBall doubleBall= new DoubleBall();
            doubleBall.setPeriod(period);
            this.deleteByEntity(doubleBall);
        }

        // step2 : 处理 数据
        List<DoubleBall> insertList = new ArrayList<DoubleBall>();

        //红球1
        DoubleBall redBall1_Record = new DoubleBall();
        redBall1_Record.setPeriod(period);
        redBall1_Record.setValue(redBall1);
        redBall1_Record.setType(BallType.RED1.getCode());

        insertList.add(redBall1_Record);

        //红球2
        DoubleBall redBall2_Record = new DoubleBall();
        redBall2_Record.setPeriod(period);
        redBall2_Record.setValue(redBall2);
        redBall2_Record.setType(BallType.RED2.getCode());

        insertList.add(redBall2_Record);

        //红球3
        DoubleBall redBall3_Record = new DoubleBall();
        redBall3_Record.setPeriod(period);
        redBall3_Record.setValue(redBall3);
        redBall3_Record.setType(BallType.RED3.getCode());

        insertList.add(redBall3_Record);

        //红球4
        DoubleBall redBall4_Record = new DoubleBall();
        redBall4_Record.setPeriod(period);
        redBall4_Record.setValue(redBall4);
        redBall4_Record.setType(BallType.RED4.getCode());

        insertList.add(redBall4_Record);

        //红球5
        DoubleBall redBall5_Record = new DoubleBall();
        redBall5_Record.setPeriod(period);
        redBall5_Record.setValue(redBall5);
        redBall5_Record.setType(BallType.RED5.getCode());

        insertList.add(redBall5_Record);

        //红球6
        DoubleBall redBall6_Record = new DoubleBall();
        redBall6_Record.setPeriod(period);
        redBall6_Record.setValue(redBall6);
        redBall6_Record.setType(BallType.RED6.getCode());

        insertList.add(redBall6_Record);

        // 蓝球
        DoubleBall blueBall_Record = new DoubleBall();
        blueBall_Record.setPeriod(period);
        blueBall_Record.setValue(blueBall);
        blueBall_Record.setType(BallType.BLUE.getCode());

        insertList.add(blueBall_Record);

        // step 3:批量插入
        this.insertList(insertList);
    }

    @Override
    public List<DoubleBall> selectValueOrderByCount(Integer type) throws Exception {
        return (List<DoubleBall>) sqlSessionTemplate.selectList("DoubleBall.selectValueOrderByCount" ,type);
    }
}
