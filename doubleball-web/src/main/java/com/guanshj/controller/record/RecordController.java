package com.guanshj.controller.record;

import com.guanshj.controller.BaseController;
import com.guanshj.framework.util.pagination.Pagination4Datatable;
import com.guanshj.model.DoubleBall;
import com.guanshj.services.DoubleBallService;
import com.guanshj.vo.DoubleBallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private DoubleBallService doubleBallService;

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
    public Object save (DoubleBallDto dto) throws Exception{
        Map<String ,Object> map = new HashMap<String, Object>();

        DoubleBall doubleBall = new DoubleBall();
        doubleBall.setPeriod(dto.getPeriod());
        List<DoubleBall> list = doubleBallService.selectByEntity(doubleBall);
        if(list !=null && !list.isEmpty()){
            map.put(AJAX_SUCCESS, "false");
            map.put(AJAX_MESSAGE, "本期数据已经存在");
            return map;
        }

        doubleBallService.saveDoubleBallRecord(dto.getPeriod(),dto.getRedBall1(),
                dto.getRedBall2(),dto.getRedBall3(),dto.getRedBall4(),dto.getRedBall5(),
                dto.getRedBall6(),dto.getBlueBall());

        map.put(AJAX_SUCCESS, "true");
        return map;
    }

    /**
     * datatable 分页数据
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "data")
    public Pagination4Datatable data (DoubleBall vo) throws Exception{
        return Pagination4Datatable.getInstance(doubleBallService.selectTableDataViewPage(vo),
                vo.getTotalResult(), vo.getsEcho());
    }
}
