package gr.codehub.restapi.repository;

import gr.codehub.restapi.repository.lib.Repository;

import javax.persistence.EntityManager;

public class BasketRepository extends Repository {

    public BasketRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return this.getClass();
    }

    @Override
    public String getEntityClassName() {
        return this.getClass().getName();
    }
}

