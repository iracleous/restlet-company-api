package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.CustomerRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.resource.CustomerResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomerResourceImpl extends ServerResource implements CustomerResource {

    private CustomerRepository customerRepository ;
    private EntityManager em;
    private long id;

    @Override
    protected void doInit() {
        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
            id = Long.parseLong(getAttribute("id"));
        }
        catch(Exception ex){

            throw new ResourceException(ex);

        }
    }

    @Override
    protected void doRelease() {
        em.close();
    }



        @Override
    public CustomerRepresentation getCustomer() throws NotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        setExisting(customer.isPresent());

        if (!customer.isPresent())  throw new NotFoundException("Customer is not found");

        CustomerRepresentation customerRepresentation = CustomerRepresentation.getCustomerRepresentation(customer.get());

        return customerRepresentation;
    }



    @Override
    public void remove() throws NotFoundException {

    }

    @Override
    public CustomerRepresentation store(CustomerRepresentation customerReprIn) throws NotFoundException, BadEntityException {
        return null;
    }
}
