package com.welab.service.impl;


import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welab.common.spring.BeanFactory;
import com.welab.dao.CityDao;
import com.welab.model.City;

/**
 * 城市业务逻辑实现类
 *
 */
@Service
public class WCityServiceUpdate implements JavaDelegate,Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1896522563435994336L;
    @Autowired
    private CityDao cityDao;

    public City findCityById(Long id) {
        return cityDao.findById(id);
    }

    @Override
    public void execute(DelegateExecution execution)  {
        String updateId= (String) execution.getVariable("updateId");
        String provinceId= (String) execution.getVariable("provinceId");
        String cityName= (String) execution.getVariable("cityName");
        String description = (String) execution.getVariable("description");
        City city= new City();
        city.setId(Long.valueOf(updateId));
        city.setProvinceId(Long.valueOf(provinceId));
        city.setCityName(cityName);
        city.setDescription(description);
        Long l=getCityDao().updateCity(city);
        execution.setVariable("updateResult", l);
    }

    private CityDao getCityDao(){
        if(cityDao==null){
            cityDao=BeanFactory.getBean(CityDao.class);
        }
        return cityDao;
    }
}