package com.exercise.FlowDeliverDemo.repository;

import com.exercise.FlowDeliverDemo.entity.Delivery;
import com.exercise.FlowDeliverDemo.entity.Plant;
import com.exercise.FlowDeliverDemo.entity.RecipientAndPrice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    public DeliveryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(Delivery delivery){
        entityManager.persist(delivery);
    }

    public Delivery find(Long id){
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }

    public void delete(Long id){
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }

    public List<Delivery> findByName(String name){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName",Delivery.class);
        query.setParameter("name",name);
        return query.getResultList();
    }

    public RecipientAndPrice getRecipientAndPrice(Long deliveryId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> criteriaQuery = criteriaBuilder.createQuery(RecipientAndPrice.class);
        Root<Plant> root = criteriaQuery.from(Plant.class);
        criteriaQuery.select(
                criteriaBuilder.construct(
                        RecipientAndPrice.class,
                        root.get("delivery").get("name"),
                        criteriaBuilder.sum(root.get("price"))))
                .where(criteriaBuilder.equal(root.get("delivery").get("id"),deliveryId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }
}
