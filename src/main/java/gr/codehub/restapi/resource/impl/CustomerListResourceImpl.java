package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.CustomerRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.resource.CustomerListResource;
import gr.codehub.restapi.resource.CustomerResource;
import gr.codehub.restapi.resource.util.ResourceUtils;
import gr.codehub.restapi.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomerListResourceImpl extends ServerResource implements CustomerListResource {
    private CustomerRepository customerRepository ;
    private EntityManager em;

    @Override
    protected void doInit() {
        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);

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
    public CustomerRepresentation add(CustomerRepresentation customerIn) throws BadEntityException {

        ResourceUtils.checkRole(this, CustomRole.ROLE_ADMIN.getRoleName());

        if (customerIn==null) throw new  BadEntityException("Null customer representation error");
        if (customerIn.getName().equals("admin")) throw new  BadEntityException("Invalid customer name error");

        Customer customer = CustomerRepresentation.getCustomer(customerIn);


        customerRepository.save(customer);


       return CustomerRepresentation.getCustomerRepresentation(customer);
    }

    @Override
    public List<CustomerRepresentation> getCustomers() throws NotFoundException {
        ResourceUtils.checkRole(this, CustomRole.ROLE_USER.getRoleName());
        List<Customer> customers= customerRepository.findAll();

        List<CustomerRepresentation> customerRepresentationList = new ArrayList<>();

        customers.forEach(customer -> customerRepresentationList.add(CustomerRepresentation.getCustomerRepresentation(customer)));

        return customerRepresentationList;

    }
}
