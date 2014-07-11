package com.guanshj.controller.record;

import com.guanshj.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:  编辑 记录 控制器： 包括录入、查询展示列表、分页、编辑记录、移除记录等功能
 * 创建日期: 14-7-11  下午2:27
 *
 * @author: guanshj QQ: 928990049
 */
@Controller
@RequestMapping(value = "/record")
public class RecordController extends BaseController{

    /**
     * 该功能模块 主要页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "")
    public ModelAndView main () throws Exception {
        ModelAndView mv = new ModelAndView("record/main");
        return mv;
    }

    /**
     * 该功能模块 添加子页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add")
    public ModelAndView addHtml() throws Exception {
        ModelAndView mv = new ModelAndView("record/add");
        return mv;
    }

    /**
     * 该功能模块 列表子页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "list")
    public ModelAndView list () throws Exception{
        ModelAndView mv = new ModelAndView("record/list");
        return mv;
    }

    /**
     * 保存数据
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "save")
    public Object save () throws Exception{
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put(AJAX_MESSAGE_SUCCESS ,"true");
        return map;
    }
}
