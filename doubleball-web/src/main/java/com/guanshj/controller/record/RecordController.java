package com.guanshj.controller.record;

import com.guanshj.constant.DoubleBallConstant;
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
     * 保存新增数据
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
            map.put(AJAX_SUCCESS, false);
            map.put(AJAX_MESSAGE, "本期数据已经存在");
            return map;
        }

        doubleBallService.saveDoubleBallRecord(dto.getPeriod(),dto.getRedBall1(),
                dto.getRedBall2(),dto.getRedBall3(),dto.getRedBall4(),dto.getRedBall5(),
                dto.getRedBall6(),dto.getBlueBall() ,DoubleBallConstant.FLAG_INSERT);

        map.put(AJAX_SUCCESS, true);
        return map;
    }

    /**
     * 修改数据
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "update")
    public Object edit (DoubleBallDto dto) throws Exception{
        Map<String ,Object> map = new HashMap<String, Object>();

        doubleBallService.saveDoubleBallRecord(dto.getPeriod(),dto.getRedBall1(),
                dto.getRedBall2(),dto.getRedBall3(),dto.getRedBall4(),dto.getRedBall5(),
                dto.getRedBall6(),dto.getBlueBall() , DoubleBallConstant.FLAG_UPDATE);

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

    /**
     *  删除 一期的记录
     * @param period
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "remove")
    public Object remove(Integer period) throws Exception {
        DoubleBall doubleBall = new DoubleBall();
        doubleBall.setPeriod(period);
        doubleBallService.deleteByEntity(doubleBall);

        Map<String ,Object> map = new HashMap<String, Object>();
        map.put(AJAX_SUCCESS, "true");
        return map;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "getByPeriod")
    public Object getByPeriod(Integer period) throws Exception{
        DoubleBall doubleBall = new DoubleBall();
        doubleBall.setPeriod(period);

        List<DoubleBallDto> list = doubleBallService.selectTableDataViewPage(doubleBall);

        if (list != null && !list.isEmpty())
            return list.get(0);
        else
            return null;
    }
}
