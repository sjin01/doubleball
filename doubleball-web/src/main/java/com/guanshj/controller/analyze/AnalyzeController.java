package com.guanshj.controller.analyze;

import com.guanshj.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 针对记录进行各种分析
 * 创建日期: 14-7-21  上午11:04
 *
 * @author: guanshj QQ: 928990049
 */
@Controller
@RequestMapping(value = "/analyze")
public class AnalyzeController extends BaseController{


    /**
     * 该功能模块 主要页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "")
    public ModelAndView main () throws Exception {
        ModelAndView mv = new ModelAndView("analyze/main");
        return mv;
    }
}
