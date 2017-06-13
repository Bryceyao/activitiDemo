package com.welab.service.workFlowTask;


import java.io.Serializable;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.welab.dao.CityDao;
import com.welab.model.City;

/**
 * 城市业务逻辑实现类
 *
 */
@Component
public class CityServiceUpdate implements Serializable{

    
    /**
     * 
     */
    private static final long serialVersionUID = 4649977894226632127L;
    @Autowired
    private CityDao cityDao;

    public void update(DelegateExecution execution,Long updateId,Long provinceId,String cityName,String description) throws Exception  {
        City city= new City();
        city.setId(updateId);
        city.setProvinceId(provinceId);
        city.setCityName(cityName);
        city.setDescription(description);
        Long l=cityDao.updateCity(city);
        execution.setVariable("updateResult", l);
    }

}