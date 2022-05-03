package com.udemy.sprint.springsel;

import org.springframework.stereotype.Component;

@Component // hands control over to the framework
public class Address {
    private String street;
    public Address(){
        this.street = "123 Main Street";

    }
    public String getStreet(){
        return  street;
    }
}
