package com.exercise.FlowDeliverDemo.service;

import com.exercise.FlowDeliverDemo.entity.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name){
        return new Plant();
    }
}
