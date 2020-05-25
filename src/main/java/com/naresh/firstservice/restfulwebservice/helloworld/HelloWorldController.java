package com.naresh.firstservice.restfulwebservice.helloworld;

import com.naresh.firstservice.restfulwebservice.helloworld.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Naresh hello world blah asfaaef";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello-world-bean");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("hello world : %s", name));
    }
}
