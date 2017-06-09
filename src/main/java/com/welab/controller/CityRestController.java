package com.welab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.welab.model.City;
import com.welab.service.CityService;

@RestController
@RequestMapping("/city")
public class CityRestController {
    
    @Autowired
    private CityService cityService;
    
    @RequestMapping(value = "/api/fcity/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/api/lcity", method = RequestMethod.GET)
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/api/ccity", method = RequestMethod.POST)
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }
    
}
