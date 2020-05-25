package com.naresh.firstservice.restfulwebservice.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class SomeBeanController {

    @GetMapping(path = "/somebean")
    public SomeBean SomeBeanStaticFiltering(){
        return new SomeBean("v1", "v2", "v3");
    }

    @GetMapping(path = "/somebean-list")
    public List<SomeBean> SomeBeanStaticFilteringAsList(){
        return Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("x1", "x2", "x3"));
    }

    @GetMapping(path = "/somebean-dynamic")
    public MappingJacksonValue SomeBeanDynamicFiltering(){
        SomeBean someBean = new SomeBean("v1", "v2", "v3");
        MappingJacksonValue mapping = getMappingJacksonValue(Arrays.asList(someBean));
        return mapping;
    }

    @GetMapping(path = "/somebean-dynamic-list")
    public MappingJacksonValue SomeBeanDynamicFilteringAsList(){
        List<SomeBean> someBeanslist = Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("x1", "x2", "x3"));
        MappingJacksonValue mapping = getMappingJacksonValue(someBeanslist);
        return mapping;
    }

    private MappingJacksonValue getMappingJacksonValue(List<SomeBean> someBeanslist) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("f3", "f2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFiter", filter);
        MappingJacksonValue mapping  = new MappingJacksonValue(someBeanslist);
        mapping.setFilters(filters);
        return mapping;
    }

//    private MappingJacksonValue addFilters(SomeBean someBean){
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("f1", "f2");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFiter", filter);
//        MappingJacksonValue mapping  = new MappingJacksonValue(someBean);
//        mapping.setFilters(filters);
//        return mapping;
//    }
}
