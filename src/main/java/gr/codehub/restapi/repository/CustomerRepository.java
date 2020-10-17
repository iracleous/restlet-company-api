package gr.codehub.restapi.repository;

import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.lib.Repository;

import javax.persistence.EntityManager;

public class CustomerRepository extends Repository<Customer,Long> {

    public CustomerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getEntityClassName() {
        return Customer.class.getName();
    }
}

