package com.exercise.FlowDeliverDemo.service;

import com.exercise.FlowDeliverDemo.entity.Plant;
import com.exercise.FlowDeliverDemo.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

    public List<Plant> findAllByName(String name){
        return plantRepository.findAllByName(name);
    }

    public List<Plant> findAll(){
        return plantRepository.findAll();
    }

    public Plant save(Plant plant){
        return plantRepository.save(plant);
    }

    public List<Plant> findAllByPriceLessThan(BigDecimal price){
        return plantRepository.findAllByPriceLessThan(price);
    }

    public Boolean isCompleted(Long id){
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id, Boolean.TRUE);
    }
}
