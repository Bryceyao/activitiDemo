package com.welab.service.workFlowTask;


import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.welab.dao.CityDao;
import com.welab.model.City;

/**
 * 城市业务逻辑实现类
 *
 */
@Component
public class CityServiceFindByExpression implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2755628975317861486L;
    @Autowired
    private CityDao cityDao;


    public void find(DelegateExecution execution,String findId1) throws Exception {
        Long findId = Long.valueOf(findId1);
        City city= cityDao.findById(findId);
        execution.setVariable("findCity", JSON.toJSONString(city));
    }

}