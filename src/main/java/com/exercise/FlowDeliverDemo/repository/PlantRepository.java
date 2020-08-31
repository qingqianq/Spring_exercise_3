package com.exercise.FlowDeliverDemo.repository;

import com.exercise.FlowDeliverDemo.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface PlantRepository extends JpaRepository<Plant, Long> {

    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    @Query("select p.delivery.completed from Plant p where p.id = :plantId")
    Boolean deliveryCompleted(Long plantId);

    //to return a wrapper class, you may need to construct it as a projection
    @Query("select new java.lang.Boolean(p.delivery.completed) from Plant p where p.id = :plantId")
    Boolean deliveryCompletedBoolean(Long plantId);

    List<Plant> findAllByName(String name);


//    public Boolean findCompletedByPlantId(Long id);

    List<Plant> findAllByPriceLessThan(BigDecimal price);
}
