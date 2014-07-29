package com.guanshj.controller;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Title :盛通-- 教育信息服务平台
 * Description:
 * 创建日期: 14-7-8  下午3:28
 *
 * @author: guanshj QQ: 928990049
 */
public class BaseController {

    protected static final String AJAX_SUCCESS = "success";
    protected static final String AJAX_MESSAGE = "message";

    protected Logger logger = Logger.getLogger(getClass());

    public void debug(String message) {
        logger.debug(message);
    }
    public void error(String message) {
        logger.error(message);
    }
    public void info(String message) {
        logger.info(message);
    }

    protected List<Integer> deWeightAndSort( List<Integer> list){
        List<Integer> result = new ArrayList<Integer>();
        // 去重组装
        for(int item : list){

            boolean isAdd = true;
            for(int result_item : result){
                if(item == result_item){
                    isAdd = false;
                    break;
                }
            }
            if(isAdd){
                result.add(item);
            }
        }

        // 冒泡排序
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size() - i -1; j++) {
                if(result.get(j) > result.get(j+1) ){
                    int temp = result.get(j);
                    result.set(j , result.get(j+1));
                    result.set(j+1 , temp);
                }
            }
        }
        return result;
    }

}
