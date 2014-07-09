package com.guanshj.controller;

import org.apache.log4j.Logger;

/**
 * Title :盛通-- 教育信息服务平台
 * Description:
 * 创建日期: 14-7-8  下午3:28
 *
 * @author: guanshj QQ: 928990049
 */
public class BaseController {

    protected static final String AJAX_MESSAGE_SUCCESS = "success";

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

}
