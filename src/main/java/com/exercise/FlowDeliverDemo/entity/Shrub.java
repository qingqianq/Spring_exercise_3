package com.exercise.FlowDeliverDemo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "shrub")
public class Shrub  extends Plant {

    private Integer widthCm;
    private Integer heightCm;

    public Shrub(Integer widthCm, Integer heightCm) {
        this.widthCm = widthCm;
        this.heightCm = heightCm;
    }

    public Shrub(Long id, String name, BigDecimal price, Delivery delivery, Integer widthCm, Integer heightCm) {
        super(id, name, price, delivery);
        this.widthCm = widthCm;
        this.heightCm = heightCm;
    }
}
