package com.welab.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.welab.model.City;

/**
 * 城市 DAO 接口类
 *
 */
@Mapper
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
    
    City findById(@Param("id") Long id);
    
    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}