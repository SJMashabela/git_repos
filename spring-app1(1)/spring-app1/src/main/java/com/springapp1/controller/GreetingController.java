package com.springapp1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController // tells spring about rest implementations
public class GreetingController {
    @Autowired
    private Greeting greeting;
    AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name")String name){
        greeting.setId(counter.incrementAndGet());
        greeting.setContent("Hi " + name + ". I am learning SpringBoot");
       return greeting;
    }
}
