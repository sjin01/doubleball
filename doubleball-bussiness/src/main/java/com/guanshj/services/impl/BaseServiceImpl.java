package com.guanshj.services.impl;

import com.guanshj.services.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 公用service接口 实现类
 * 创建日期: 14-7-9  上午11:13
 *
 * @author: guanshj QQ: 928990049
 */
public class BaseServiceImpl implements BaseService{

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
