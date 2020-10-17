package gr.codehub.restapi.repository;

import gr.codehub.restapi.model.Basket;
import gr.codehub.restapi.repository.lib.Repository;

import javax.persistence.EntityManager;

public class BasketRepository extends Repository<Basket,Long> {

    public BasketRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Basket> getEntityClass() {
        return Basket.class;
    }

    @Override
    public String getEntityClassName() {
        return Basket.class.getName();
    }
}

