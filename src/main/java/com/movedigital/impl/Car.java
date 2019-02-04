package com.movedigital.impl;


import org.springframework.stereotype.Component;

//@Component
public class Car {

    private String marque;

    public Car(String marque) {
        this.marque = marque;
    }

    public void display() {
        System.out.println("I am a car de type " + marque);
    }

}
