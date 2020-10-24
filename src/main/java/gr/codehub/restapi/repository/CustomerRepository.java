package gr.codehub.restapi.repository;

import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.lib.Repository;
import gr.codehub.restapi.representation.CustomerRepresentation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends Repository<Customer,Long> {
private EntityManager entityManager;
    public CustomerRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getEntityClassName() {
        return Customer.class.getName();
    }



    public List<Customer>  findByAddress(String address){
        List<Customer> cs = entityManager.createQuery("from Customer c WHERE   c.address = :address ")
                .setParameter("address", address)
                .getResultList();
        return cs;
    }


}

