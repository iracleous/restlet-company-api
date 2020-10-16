package gr.codehub.restapi.repository;

import gr.codehub.restapi.repository.lib.Repository;

import javax.persistence.EntityManager;

public class CustomerRepository extends Repository {

    public CustomerRepository(EntityManager entityManager) {
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

