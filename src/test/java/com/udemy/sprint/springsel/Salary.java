package com.udemy.sprint.springsel;

import org.springframework.stereotype.Component;

@Component
public class Salary {
    private int amount;
    public Salary(){
        this.amount = 100;
    }
    public int getAmount(){
        return  amount;
    }
}
