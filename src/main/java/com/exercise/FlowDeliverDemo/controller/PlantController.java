package com.exercise.FlowDeliverDemo.controller;


import com.exercise.FlowDeliverDemo.entity.Plant;
import com.exercise.FlowDeliverDemo.entity.PlantDTO;
import com.exercise.FlowDeliverDemo.entity.Views;
import com.exercise.FlowDeliverDemo.service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlantByName(name);
        return new PlantDTO(plant.getName(),plant.getPrice());
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }
}
