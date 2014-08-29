package com.guanshj.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 创建日期: 14-7-9  上午11:22
 *
 * @author: guanshj QQ: 928990049
 *
 *                          _oo0oo_
 *                         o8888888o
 *                         88" . "88
 *                         (| -_- |)
 *                         0\  =  /0
 *                       ___/'---'\___
 *                     .' \\|     |// '.
 *                    / \\|||  :  |||// \
 *                   / _||||| -:- |||||_ \
 *                  |   | \\\  -  /// |   |
 *                  | \_|  ''\---/''  |_/ |
 *                  \  .-\__  '-'  __/-.  /
 *               ____'. .'  /--.--\  '. .'____
 *            .""  '<  `.___\_<|>_/___.'  >'  "".
 *           | | :   `_ \`.;`\ _ /`;.`/ _'   : | |
 *           \  \  `_.   \_ __\ /__ _/   ._'  /  /
 *       =====`-._____`.___ \_____/ ___.`_____.-`=====
 *                          '=---='
 *
 *       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *                     佛祖保佑   中500万
 *
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

    /**
     * 首页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "")
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("index");

        if (session.getAttribute("context_path") == null) {
            session.setAttribute("context_path", request.getContextPath());
        }

        return mv;
    }
}
