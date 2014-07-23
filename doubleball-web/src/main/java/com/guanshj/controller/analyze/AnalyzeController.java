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

        Integer sRed1 = AnalyzeUtil.sRed1(currentRed);
        sRedList.add(sRed1);
        mv.addObject("sRed1",sRed1);


        mv.addObject("sRedList",sRedList);  // 全部杀得红球
        mv.addObject("sBlueList",sBlueList); // 全部杀的蓝球
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
        return mv;
    }


}
