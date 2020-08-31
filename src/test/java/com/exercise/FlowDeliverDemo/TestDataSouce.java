package com.exercise.FlowDeliverDemo;

import com.exercise.FlowDeliverDemo.entity.Delivery;
import com.exercise.FlowDeliverDemo.entity.Plant;
import com.exercise.FlowDeliverDemo.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class TestDataSouce {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    PlantRepository plantRepository;

    @Test
    void testPriceLessThan(){
        Plant p = testEntityManager.persist(new Plant("Foo leaf", BigDecimal.valueOf(4.99)));
        testEntityManager.persist(new Plant("test plant", BigDecimal.valueOf(5.01)));

        List<Plant> cheapPlants = plantRepository.findAllByPriceLessThan(BigDecimal.valueOf(5));
        Assertions.assertEquals(1, cheapPlants.size(), "SIZE");
        Assertions.assertEquals(p.getId(),cheapPlants.get(0).getId(),"Id");
    }

    @Test
    public void testDeliveryCompleted() {
        Plant p = testEntityManager.persist(new Plant("Baz Root", BigDecimal.valueOf(9.99)));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein", "234 West Side", LocalDateTime.now()));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);

        //test both before and after
        Assertions.assertNotEquals(p.getId(),null);
        Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }
}

