package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        // businessLogic
//        process(name);
        return "Hello " + name;
    }

//    private void process(String name){
//
//    }
}
