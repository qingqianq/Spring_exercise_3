package com.exercise.FlowDeliverDemo.service;

import com.exercise.FlowDeliverDemo.entity.Delivery;
import com.exercise.FlowDeliverDemo.entity.RecipientAndPrice;
import com.exercise.FlowDeliverDemo.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery){
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public RecipientAndPrice getRecipientAndPrice(Long deliveryId){
        return deliveryRepository.getRecipientAndPrice(deliveryId);
    }
}
