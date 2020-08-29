package com.exercise.FlowDeliverDemo.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "flower")
public class Flower extends Plant {

    private String color;

    public Flower(){

    }

    public Flower(String color) {
        this.color = color;
    }

    public Flower(Long id, String name, BigDecimal price, Delivery delivery, String color) {
        super(id, name, price, delivery);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
