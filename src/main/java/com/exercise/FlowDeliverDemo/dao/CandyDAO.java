package com.exercise.FlowDeliverDemo.dao;

import com.exercise.FlowDeliverDemo.entity.CandyData;

import java.math.BigDecimal;
import java.util.List;

public interface CandyDAO {
    List<CandyData> findAll();
    CandyData create(String name, BigDecimal price);
    List<CandyData> getCandyByDeliveryId(Long deliveryId);
    void addCandyToDelivery(Long candyId, Long deliveryId);
}
