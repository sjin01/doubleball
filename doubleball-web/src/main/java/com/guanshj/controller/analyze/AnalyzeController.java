package com.guanshj.controller.analyze;

import com.guanshj.controller.BaseController;
import com.guanshj.enums.BallType;
import com.guanshj.framework.util.AnalyzeUtil;
import com.guanshj.model.DoubleBall;
import com.guanshj.services.DoubleBallService;
import com.guanshj.vo.DoubleBallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 针对记录进行各种分析
 * 创建日期: 14-7-21  上午11:04
 *
 * @author: guanshj QQ: 928990049
 */
@Controller
@RequestMapping(value = "/analyze")
public class AnalyzeController extends BaseController{

    @Autowired
    private DoubleBallService doubleBallService;

    /**
     * 该功能模块 主要页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "")
    public ModelAndView main ( Integer period ) throws Exception {
        ModelAndView mv = new ModelAndView("analyze/main");

        if(period == null || period==0){
            period = doubleBallService.selectCurrentPeriod();
        }
        mv.addObject("period",period);

        return mv;
    }

    /**
     * 概览统计---
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "overview")
    public ModelAndView overview (Integer period) throws Exception{
        ModelAndView mv = new ModelAndView("analyze/overview");

        // 本期号码：
        DoubleBall po = new DoubleBall();
        po.setPeriod(period);
        List<DoubleBall> list = doubleBallService.selectByEntity(po);

        List<Integer> currentRed = new ArrayList<Integer>();   // 六个红球
        Integer currentBlue = 0;  // 蓝球

        for(DoubleBall item : list){
            if(item.getType() == BallType.BLUE.getCode()){
                // 蓝
                currentBlue = item.getValue();
            }else{
                // 红
                currentRed.add(item.getValue());
            }
        }
        mv.addObject("currentPeriod" ,period);
        mv.addObject("currentRed" ,currentRed);
        mv.addObject("currentBlue" ,currentBlue);


        // 上期号码：
        period = period -1;
        po = new DoubleBall();
        po.setPeriod(period);
        list = doubleBallService.selectByEntity(po);

        if(list!=null && !list.isEmpty()){  // 也许没有上一期的数据呢
            List<Integer> prevRed = new ArrayList<Integer>();   // 六个红球
            Integer prevBlue = 0;  // 蓝球

            for(DoubleBall item : list){
                if(item.getType() == BallType.BLUE.getCode()){
                    // 蓝
                    prevBlue = item.getValue();
                }else{
                    // 红
                    prevRed.add(item.getValue());
                }
            }
            mv.addObject("prevPeriod" ,period);
            mv.addObject("prevRed" ,prevRed);
            mv.addObject("prevBlue" ,prevBlue);
        }

        // 最多开出号码：
        mv.addObject("maxRed" , doubleBallService.selectValueOrderByCount(0));
        mv.addObject("maxBlue" ,doubleBallService.selectValueOrderByCount(BallType.BLUE.getCode()));

        return mv;
    }

    /**
     * currentAnalyze --- 针对当前一期来分析
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "currentAnalyze")
    public ModelAndView currentAnalyze ( Integer period) throws Exception{
        ModelAndView mv = new ModelAndView("analyze/currentAnalyze");

        // 本期号码：
        DoubleBall po = new DoubleBall();
        po.setPeriod(period);
        List<DoubleBall> list = doubleBallService.selectByEntity(po);

        List<Integer> currentRed = new ArrayList<Integer>();   // 六个红球
        Integer currentBlue = 0;  // 蓝球

        for(DoubleBall item : list){
            if(item.getType() == BallType.BLUE.getCode()){
                // 蓝
                currentBlue = item.getValue();
            }else{
                // 红
                currentRed.add(item.getValue());
            }
        }
        mv.addObject("currentPeriod" ,period);
        mv.addObject("currentRed" ,currentRed);
        mv.addObject("currentBlue" ,currentBlue);


        // 上期号码：
        period = period -1;
        po = new DoubleBall();
        po.setPeriod(period);
        list = doubleBallService.selectByEntity(po);

        List<Integer> prevRed = new ArrayList<Integer>();   // 六个红球
        Integer prevBlue = 0;  // 蓝球

        if(list!=null && !list.isEmpty()){  // 也许没有上一期的数据呢
            for(DoubleBall item : list){
                if(item.getType() == BallType.BLUE.getCode()){
                    // 蓝
                    prevBlue = item.getValue();
                }else{
                    // 红
                    prevRed.add(item.getValue());
                }
            }
            mv.addObject("prevPeriod" ,period);
            mv.addObject("prevRed" ,prevRed);
            mv.addObject("prevBlue" ,prevBlue);
        }

        // 上2期号码：
        /*period = period -1;
        po = new DoubleBall();
        po.setPeriod(period);
        list = doubleBallService.selectByEntity(po);

        List<Integer> prev2Red = new ArrayList<Integer>();   // 六个红球
        Integer prev2Blue = 0;  // 蓝球

        if(list!=null && !list.isEmpty()){  // 也许没有上一期的数据呢
            for(DoubleBall item : list){
                if(item.getType() == BallType.BLUE.getCode()){
                    // 蓝
                    prev2Blue = item.getValue();
                }else{
                    // 红
                    prev2Red.add(item.getValue());
                }
            }
            mv.addObject("prev2Period" ,period);
            mv.addObject("prev2Red" ,prev2Red);
            mv.addObject("prev2Blue" ,prev2Blue);
        }*/

        // TODO 排除开始（本期分析）
        List<Integer> sRedList = new ArrayList<Integer>();
        List<Integer> sBlueList = new ArrayList<Integer>();

        Integer sRed1 = AnalyzeUtil.sRed1(currentRed);
        sRedList.add(sRed1);
        mv.addObject("sRed1",sRed1);

        Integer sRed2 = AnalyzeUtil.sRed2(currentRed);
        sRedList.add(sRed2);
        mv.addObject("sRed2",sRed2);

        Integer sRed3 = AnalyzeUtil.sRed3(currentRed);
        sRedList.add(sRed3);
        mv.addObject("sRed3",sRed3);

        Integer sRed4 = AnalyzeUtil.sRed4(currentRed);
        sRedList.add(sRed4);
        mv.addObject("sRed4",sRed4);

        Integer sRed5 = AnalyzeUtil.sRed5(currentRed ,currentBlue);
        sRedList.add(sRed5);
        mv.addObject("sRed5",sRed5);

        Integer sRed6 = AnalyzeUtil.sRed6(currentRed);
        sRedList.add(sRed6);
        mv.addObject("sRed6",sRed6);

        Integer sRed7 = AnalyzeUtil.sRed7(currentRed);
        sRedList.add(sRed7);
        mv.addObject("sRed7",sRed7);

        Integer sRed8 = AnalyzeUtil.sRed8(currentRed);
        sRedList.add(sRed8);
        mv.addObject("sRed8",sRed8);

        Integer sRed9 = AnalyzeUtil.sRed9(currentRed);
        sRedList.add(sRed9);
        mv.addObject("sRed9",sRed9);

        Integer sRed10 = AnalyzeUtil.sRed10(currentRed);
        sRedList.add(sRed10);
        mv.addObject("sRed10",sRed10);

        Integer sRed11 = AnalyzeUtil.sRed11(currentRed , currentBlue);
        sRedList.add(sRed11);
        mv.addObject("sRed11",sRed11);

        // 杀红 12- 17是 出号顺序， 暂没做处理

        Integer sRed18 = AnalyzeUtil.sRed18(currentRed , currentBlue);
        sRedList.add(sRed18);
        mv.addObject("sRed18",sRed18);

        Integer sRed19 = AnalyzeUtil.sRed19(currentRed , currentBlue);
        sRedList.add(sRed19);
        mv.addObject("sRed19",sRed19);

        Integer sRed20 = AnalyzeUtil.sRed20(currentRed , currentBlue);
        sRedList.add(sRed20);
        mv.addObject("sRed20",sRed20);

        Integer sRed21 = AnalyzeUtil.sRed21(currentRed , currentBlue);
        sRedList.add(sRed21);
        mv.addObject("sRed21",sRed21);

        Integer sRed22 = AnalyzeUtil.sRed22(currentRed , currentBlue);
        sRedList.add(sRed22);
        mv.addObject("sRed22",sRed22);

        Integer sRed23 = AnalyzeUtil.sRed23(currentBlue);
        sRedList.add(sRed23);
        mv.addObject("sRed23",sRed23);

        Integer sRed24 = AnalyzeUtil.sRed24(currentBlue);
        sRedList.add(sRed24);
        mv.addObject("sRed24",sRed24);

        Integer sRed25 = AnalyzeUtil.sRed25(currentBlue);
        sRedList.add(sRed25);
        mv.addObject("sRed25",sRed25);

        Integer sRed26 = AnalyzeUtil.sRed26(currentBlue);
        sRedList.add(sRed26);
        mv.addObject("sRed26",sRed26);

        Integer sRed27 = AnalyzeUtil.sRed27(currentRed , currentBlue);
        sRedList.add(sRed27);
        mv.addObject("sRed27",sRed27);

        Integer sRed28 = AnalyzeUtil.sRed28(currentRed );
        sRedList.add(sRed28);
        mv.addObject("sRed28",sRed28);

        Integer sRed29 = AnalyzeUtil.sRed29(currentRed , currentBlue);
        sRedList.add(sRed29);
        mv.addObject("sRed29",sRed29);

        Integer sRed30 = AnalyzeUtil.sRed30(currentRed );
        sRedList.add(sRed30);
        mv.addObject("sRed30",sRed30);

        Integer sRed31 = AnalyzeUtil.sRed31(currentRed );
        sRedList.add(sRed31);
        mv.addObject("sRed31",sRed31);

        // blue bugin -----------

        List<Integer> sBlue1 = AnalyzeUtil.sBlue1(currentBlue);
        for(Integer item : sBlue1){
            sBlueList.add(item);
        }
        mv.addObject("sBlue1" , sBlue1);

        List<Integer> sBlue2 = AnalyzeUtil.sBlue2(currentBlue);
        for(Integer item : sBlue2){
            sBlueList.add(item);
        }
        mv.addObject("sBlue2" , sBlue2);

        List<Integer> sBlue3 = AnalyzeUtil.sBlue3(currentBlue);
        for(Integer item : sBlue3){
            sBlueList.add(item);
        }
        mv.addObject("sBlue3" , sBlue3);

        List<Integer> sBlue4 = AnalyzeUtil.sBlue4(currentBlue ,prevBlue);
        for(Integer item : sBlue4){
            sBlueList.add(item);
        }
        mv.addObject("sBlue4" , sBlue4);

        List<Integer> sBlue5 = AnalyzeUtil.sBlue5(currentBlue,prevBlue);
        for(Integer item : sBlue5){
            sBlueList.add(item);
        }
        mv.addObject("sBlue5" , sBlue5);

        Integer sBlue6 = AnalyzeUtil.sBlue6(currentBlue,prevBlue);
        sBlueList.add(sBlue6);
        mv.addObject("sBlue6" , sBlue6);

        Integer sBlue7 = AnalyzeUtil.sBlue7(currentBlue,prevBlue);
        sBlueList.add(sBlue7);
        mv.addObject("sBlue7" , sBlue7);

        List<Integer> sBlue8 = AnalyzeUtil.sBlue8(currentBlue);
        for(Integer item : sBlue8){
            sBlueList.add(item);
        }
        mv.addObject("sBlue8" , sBlue8);

        List<Integer> sBlue9 = AnalyzeUtil.sBlue9(currentBlue);
        for(Integer item : sBlue9){
            sBlueList.add(item);
        }
        mv.addObject("sBlue9" , sBlue9);

        List<Integer> sBlue10 = AnalyzeUtil.sBlue10(currentBlue);
        for(Integer item : sBlue10){
            sBlueList.add(item);
        }
        mv.addObject("sBlue10" , sBlue10);

        List<Integer> sBlue11 = AnalyzeUtil.sBlue11(currentBlue);
        for(Integer item : sBlue11){
            sBlueList.add(item);
        }
        mv.addObject("sBlue11" , sBlue11);

        List<Integer> sBlue12 = AnalyzeUtil.sBlue12(currentBlue);
        for(Integer item : sBlue12){
            sBlueList.add(item);
        }
        mv.addObject("sBlue12" , sBlue12);


        mv.addObject("sRedList", this.deWeightAndSort(sRedList));  // 全部杀得红球
        mv.addObject("sBlueList",this.deWeightAndSort(sBlueList)); // 全部杀的蓝球
        return mv;
    }

    /**
     * prevAnalyze --- 针对上一期的分析
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "prevAnalyze")
    public ModelAndView prevAnalyze ( Integer period) throws Exception{
        ModelAndView mv = new ModelAndView("analyze/prevAnalyze");
        // 本期号码：
        /*DoubleBall po = new DoubleBall();
        po.setPeriod(period);
        List<DoubleBall> list = doubleBallService.selectByEntity(po);

        List<Integer> currentRed = new ArrayList<Integer>();   // 六个红球
        Integer currentBlue = 0;  // 蓝球

        for(DoubleBall item : list){
            if(item.getType() == BallType.BLUE.getCode()){
                // 蓝
                currentBlue = item.getValue();
            }else{
                // 红
                currentRed.add(item.getValue());
            }
        }
        mv.addObject("currentPeriod" ,period);
        mv.addObject("currentRed" ,currentRed);
        mv.addObject("currentBlue" ,currentBlue);*/


        // 上期号码：
        period = period -1;
        DoubleBall po = new DoubleBall();
        po.setPeriod(period);
        List<DoubleBall> list = doubleBallService.selectByEntity(po);

        List<Integer> prevRed = new ArrayList<Integer>();   // 六个红球
        Integer prevBlue = 0;  // 蓝球

        if(list!=null && !list.isEmpty()){  // 也许没有上一期的数据呢
            for(DoubleBall item : list){
                if(item.getType() == BallType.BLUE.getCode()){
                    // 蓝
                    prevBlue = item.getValue();
                }else{
                    // 红
                    prevRed.add(item.getValue());
                }
            }
            mv.addObject("prevPeriod" ,period);
            mv.addObject("prevRed" ,prevRed);
            mv.addObject("prevBlue" ,prevBlue);
        }

        // 上2期号码：
        period = period -1;
        po = new DoubleBall();
        po.setPeriod(period);
        list = doubleBallService.selectByEntity(po);

        List<Integer> prev2Red = new ArrayList<Integer>();   // 六个红球
        Integer prev2Blue = 0;  // 蓝球

        if(list!=null && !list.isEmpty()){  // 也许没有上一期的数据呢
            for(DoubleBall item : list){
                if(item.getType() == BallType.BLUE.getCode()){
                    // 蓝
                    prev2Blue = item.getValue();
                }else{
                    // 红
                    prev2Red.add(item.getValue());
                }
            }
            mv.addObject("prev2Period" ,period);
            mv.addObject("prev2Red" ,prev2Red);
            mv.addObject("prev2Blue" ,prev2Blue);
        }

        // TODO 排除开始（本期分析）
        List<Integer> sRedList = new ArrayList<Integer>();
        List<Integer> sBlueList = new ArrayList<Integer>();

        Integer sRed1 = AnalyzeUtil.sRed1(prevRed);
        sRedList.add(sRed1);
        mv.addObject("sRed1",sRed1);

        Integer sRed2 = AnalyzeUtil.sRed2(prevRed);
        sRedList.add(sRed2);
        mv.addObject("sRed2",sRed2);

        Integer sRed3 = AnalyzeUtil.sRed3(prevRed);
        sRedList.add(sRed3);
        mv.addObject("sRed3",sRed3);

        Integer sRed4 = AnalyzeUtil.sRed4(prevRed);
        sRedList.add(sRed4);
        mv.addObject("sRed4",sRed4);

        Integer sRed5 = AnalyzeUtil.sRed5(prevRed ,prevBlue);
        sRedList.add(sRed5);
        mv.addObject("sRed5",sRed5);

        Integer sRed6 = AnalyzeUtil.sRed6(prevRed);
        sRedList.add(sRed6);
        mv.addObject("sRed6",sRed6);

        Integer sRed7 = AnalyzeUtil.sRed7(prevRed);
        sRedList.add(sRed7);
        mv.addObject("sRed7",sRed7);

        Integer sRed8 = AnalyzeUtil.sRed8(prevRed);
        sRedList.add(sRed8);
        mv.addObject("sRed8",sRed8);

        Integer sRed9 = AnalyzeUtil.sRed9(prevRed);
        sRedList.add(sRed9);
        mv.addObject("sRed9",sRed9);

        Integer sRed10 = AnalyzeUtil.sRed10(prevRed);
        sRedList.add(sRed10);
        mv.addObject("sRed10",sRed10);

        Integer sRed11 = AnalyzeUtil.sRed11(prevRed , prevBlue);
        sRedList.add(sRed11);
        mv.addObject("sRed11",sRed11);

        // 杀红 12- 17是 出号顺序， 暂没做处理

        Integer sRed18 = AnalyzeUtil.sRed18(prevRed , prevBlue);
        sRedList.add(sRed18);
        mv.addObject("sRed18",sRed18);

        Integer sRed19 = AnalyzeUtil.sRed19(prevRed , prevBlue);
        sRedList.add(sRed19);
        mv.addObject("sRed19",sRed19);

        Integer sRed20 = AnalyzeUtil.sRed20(prevRed , prevBlue);
        sRedList.add(sRed20);
        mv.addObject("sRed20",sRed20);

        Integer sRed21 = AnalyzeUtil.sRed21(prevRed , prevBlue);
        sRedList.add(sRed21);
        mv.addObject("sRed21",sRed21);

        Integer sRed22 = AnalyzeUtil.sRed22(prevRed , prevBlue);
        sRedList.add(sRed22);
        mv.addObject("sRed22",sRed22);

        Integer sRed23 = AnalyzeUtil.sRed23(prevBlue);
        sRedList.add(sRed23);
        mv.addObject("sRed23",sRed23);

        Integer sRed24 = AnalyzeUtil.sRed24(prevBlue);
        sRedList.add(sRed24);
        mv.addObject("sRed24",sRed24);

        Integer sRed25 = AnalyzeUtil.sRed25(prevBlue);
        sRedList.add(sRed25);
        mv.addObject("sRed25",sRed25);

        Integer sRed26 = AnalyzeUtil.sRed26(prevBlue);
        sRedList.add(sRed26);
        mv.addObject("sRed26",sRed26);

        Integer sRed27 = AnalyzeUtil.sRed27(prevRed , prevBlue);
        sRedList.add(sRed27);
        mv.addObject("sRed27",sRed27);

        Integer sRed28 = AnalyzeUtil.sRed28(prevRed );
        sRedList.add(sRed28);
        mv.addObject("sRed28",sRed28);

        Integer sRed29 = AnalyzeUtil.sRed29(prevRed , prevBlue);
        sRedList.add(sRed29);
        mv.addObject("sRed29",sRed29);

        Integer sRed30 = AnalyzeUtil.sRed30(prevRed );
        sRedList.add(sRed30);
        mv.addObject("sRed30",sRed30);

        Integer sRed31 = AnalyzeUtil.sRed31(prevRed );
        sRedList.add(sRed31);
        mv.addObject("sRed31",sRed31);

        // blue bugin -----------

        List<Integer> sBlue1 = AnalyzeUtil.sBlue1(prevBlue);
        for(Integer item : sBlue1){
            sBlueList.add(item);
        }
        mv.addObject("sBlue1" , sBlue1);

        List<Integer> sBlue2 = AnalyzeUtil.sBlue2(prevBlue);
        for(Integer item : sBlue2){
            sBlueList.add(item);
        }
        mv.addObject("sBlue2" , sBlue2);

        List<Integer> sBlue3 = AnalyzeUtil.sBlue3(prevBlue);
        for(Integer item : sBlue3){
            sBlueList.add(item);
        }
        mv.addObject("sBlue3" , sBlue3);

        List<Integer> sBlue4 = AnalyzeUtil.sBlue4(prevBlue ,prev2Blue);
        for(Integer item : sBlue4){
            sBlueList.add(item);
        }
        mv.addObject("sBlue4" , sBlue4);

        List<Integer> sBlue5 = AnalyzeUtil.sBlue5(prevBlue,prev2Blue);
        for(Integer item : sBlue5){
            sBlueList.add(item);
        }
        mv.addObject("sBlue5" , sBlue5);

        Integer sBlue6 = AnalyzeUtil.sBlue6(prevBlue,prev2Blue);
        sBlueList.add(sBlue6);
        mv.addObject("sBlue6" , sBlue6);

        Integer sBlue7 = AnalyzeUtil.sBlue7(prevBlue,prev2Blue);
        sBlueList.add(sBlue7);
        mv.addObject("sBlue7" , sBlue7);

        List<Integer> sBlue8 = AnalyzeUtil.sBlue8(prevBlue);
        for(Integer item : sBlue8){
            sBlueList.add(item);
        }
        mv.addObject("sBlue8" , sBlue8);

        List<Integer> sBlue9 = AnalyzeUtil.sBlue9(prevBlue);
        for(Integer item : sBlue9){
            sBlueList.add(item);
        }
        mv.addObject("sBlue9" , sBlue9);

        List<Integer> sBlue10 = AnalyzeUtil.sBlue10(prevBlue);
        for(Integer item : sBlue10){
            sBlueList.add(item);
        }
        mv.addObject("sBlue10" , sBlue10);

        List<Integer> sBlue11 = AnalyzeUtil.sBlue11(prevBlue);
        for(Integer item : sBlue11){
            sBlueList.add(item);
        }
        mv.addObject("sBlue11" , sBlue11);

        List<Integer> sBlue12 = AnalyzeUtil.sBlue12(prevBlue);
        for(Integer item : sBlue12){
            sBlueList.add(item);
        }
        mv.addObject("sBlue12" , sBlue12);


        mv.addObject("sRedList", this.deWeightAndSort(sRedList));  // 全部杀得红球
        mv.addObject("sBlueList",this.deWeightAndSort(sBlueList)); // 全部杀的蓝球
        return mv;
    }


}
