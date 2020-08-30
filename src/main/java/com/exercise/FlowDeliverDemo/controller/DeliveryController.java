package com.exercise.FlowDeliverDemo.controller;

import com.exercise.FlowDeliverDemo.entity.Delivery;
import com.exercise.FlowDeliverDemo.entity.RecipientAndPrice;
import com.exercise.FlowDeliverDemo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId){
        return deliveryService.getRecipientAndPrice(deliveryId);
    }

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery){
        return deliveryService.save(delivery);
    }
}
