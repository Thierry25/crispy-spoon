package com.thierry.webservices.restfulwebservices.controllers.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.thierry.webservices.restfulwebservices.services.FilterService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    private FilterService filterService;

    public FilteringController(FilterService filterService){
        this.filterService = filterService;
    }

    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        var data = new SomeBean("value1", "value2", "value3");

        var entityModel = EntityModel.of(data);
        var link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).beansList());
        entityModel.add(link.withRel("all-filtered"));

        return filterService.filter(entityModel, "MyFilter", "field1", "field2");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue beansList(){
       var beans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
               new SomeBean("value4", "value5", "value6"));


       return filterService.filter(beans, "MyFilter", "field2", "field3");
    }
}
