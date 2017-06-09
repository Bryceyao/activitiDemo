package com.welab.service.impl;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welab.dao.CityDao;
import com.welab.model.City;
import com.welab.service.CityService;

/**
 * 城市业务逻辑实现类
 *
 */
@Service
public class CityServiceImpl implements CityService,Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4674539838143417523L;
    
    
    @Autowired
    private CityDao cityDao;

    public List<City> findAllCity(){
        return cityDao.findAllCity();
    }

    public City findCityById(Long id) {
        return cityDao.findById(id);
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return cityDao.deleteCity(id);
    }

  
}