package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.CustomerRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.resource.CustomerResource;
import gr.codehub.restapi.resource.util.ResourceUtils;
import gr.codehub.restapi.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerResourceImpl extends ServerResource implements CustomerResource {

    private CustomerRepository customerRepository;
    private EntityManager em;
    private long id;
private boolean hasId;
    @Override
    protected void doInit() {
        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
            id = Long.parseLong(getAttribute("id"));
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }

    }

    @Override
    protected void doRelease() {
        em.close();
    }

    @Override
    public CustomerRepresentation getCustomer() throws NotFoundException, ResourceException {

        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_ADMIN.getRoleName());
        roles.add(CustomRole.ROLE_USER.getRoleName());

        ResourceUtils.checkRole(this, roles);
    Optional<Customer> customer = customerRepository.findById(id);
    setExisting(customer.isPresent());
    if (!customer.isPresent()) throw new NotFoundException("Customer is not found");
    CustomerRepresentation customerRepresentation = CustomerRepresentation.getCustomerRepresentation(customer.get());
    return customerRepresentation;

    }

    @Override
    public void remove() throws NotFoundException {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_ADMIN.getRoleName());

        ResourceUtils.checkRole(this, roles);
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerRepresentation update(CustomerRepresentation customerReprIn) throws NotFoundException, BadEntityException {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_ADMIN.getRoleName());

        ResourceUtils.checkRole(this, roles);
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (!customerOpt.isPresent()) throw new NotFoundException("The given customer id is not existing");
        Customer customer = customerOpt.get();

        customer.setName(customerReprIn.getName());
        customer.setDob(customerReprIn.getDob());
        customer.setAddress(customerReprIn.getAddress());
        customer.setCategory(customerReprIn.getCategory());

        customerRepository.save(customer);
        return CustomerRepresentation.getCustomerRepresentation(customer);
    }
}
