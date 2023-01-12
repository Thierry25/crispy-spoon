package com.thierry.webservices.restfulwebservices.services;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    public MappingJacksonValue filter(Object dataToFilter, String filterName, String... fieldsToReturn){
        var mapping = new MappingJacksonValue(dataToFilter);
        var filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToReturn);
        var filters = new SimpleFilterProvider().addFilter(filterName, filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
