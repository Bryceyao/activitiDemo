package com.welab.service.impl;


import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.welab.common.spring.BeanFactory;
import com.welab.dao.CityDao;
import com.welab.model.City;

/**
 * 城市业务逻辑实现类
 *
 */
@Service
public class WCityServiceFind implements JavaDelegate,Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2755628975317861486L;
    @Autowired
    private CityDao cityDao;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String findId = (String) execution.getVariable("findId");
        Long id = Long.valueOf(findId);
        City city= getCityDao().findById(id);
        execution.setVariable("findCity", JSON.toJSONString(city));
    }

    private CityDao getCityDao(){
        if(cityDao==null){
            cityDao=BeanFactory.getBean(CityDao.class);
        }
        return cityDao;
    }
}