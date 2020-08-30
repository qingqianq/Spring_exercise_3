package com.exercise.FlowDeliverDemo.controller;


import com.exercise.FlowDeliverDemo.entity.Plant;
import com.exercise.FlowDeliverDemo.entity.PlantDTO;
import com.exercise.FlowDeliverDemo.entity.Views;
import com.exercise.FlowDeliverDemo.service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        List<Plant> plants = plantService.findAllByName(name);
        Plant plant = plants.get(0);
        if(plant != null)
            return new PlantDTO(plant.getName(),plant.getPrice());
        return null;
    }

    @GetMapping
//    @JsonView(Views.Public.class)
    public List<Plant> getAllPlants(){
        return plantService.findAll();
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.isCompleted(id);
    }

    @GetMapping("/under-price/{price}")
    @JsonView(Views.Public.class)
    public List<Plant> plantsCheaperThan(@PathVariable BigDecimal price) {
        return plantService.findAllByPriceLessThan(price);
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.findAllByName(name).get(0);
    }
}