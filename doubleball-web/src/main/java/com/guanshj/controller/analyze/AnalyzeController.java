package com.guanshj.controller.analyze;

import com.guanshj.controller.BaseController;
import com.guanshj.enums.BallType;
import com.guanshj.model.DoubleBall;
import com.guanshj.services.DoubleBallService;
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
    public ModelAndView main () throws Exception {
        ModelAndView mv = new ModelAndView("analyze/main");

        // 本期号码：
        Integer period = doubleBallService.selectCurrentPeriod();
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


}
