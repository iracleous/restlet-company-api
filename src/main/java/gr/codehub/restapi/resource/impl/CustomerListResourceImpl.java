package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.repository.CustomerRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.resource.CustomerListResource;
import gr.codehub.restapi.resource.util.ResourceUtils;
import gr.codehub.restapi.security.CustomRole;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomerListResourceImpl extends ServerResource implements CustomerListResource {
    private CustomerRepository customerRepository;
    private EntityManager em;

    @Override
    protected void doInit() {
        try {
            em = JpaUtil.getEntityManager();
            customerRepository = new CustomerRepository(em);
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }
    }

    @Override
    protected void doRelease() {
        em.close();
    }

    @Override
    public CustomerRepresentation add(CustomerRepresentation customerIn) throws BadEntityException {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_ADMIN.getRoleName());

        ResourceUtils.checkRole(this, roles);
        if (customerIn == null) throw new BadEntityException("Null customer representation error");
        if (customerIn.getName().equals("admin")) throw new BadEntityException("Invalid customer name error");
        Customer customer = CustomerRepresentation.getCustomer(customerIn);
        customerRepository.save(customer);
        return CustomerRepresentation.getCustomerRepresentation(customer);
    }

    @Override
    public List<CustomerRepresentation> getCustomers() throws NotFoundException {
        List<String> roles = new ArrayList<>();
        roles.add(CustomRole.ROLE_ADMIN.getRoleName());
        roles.add(CustomRole.ROLE_USER.getRoleName());
        ResourceUtils.checkRole(this, roles);

        try {

            String address = getQueryValue("address");
if (address== null || address.equals("")) throw new Exception();
            List<Customer> customers = customerRepository.findByAddress(address);
            List<CustomerRepresentation> customerRepresentationList = new ArrayList<>();
            customers.forEach(customer -> customerRepresentationList.add(CustomerRepresentation.getCustomerRepresentation(customer)));
            return customerRepresentationList;
        } catch (Exception e) {
        }
        List<Customer> customers = customerRepository.findAll();
        List<CustomerRepresentation> customerRepresentationList = new ArrayList<>();
        customers.forEach(customer -> customerRepresentationList.add(CustomerRepresentation.getCustomerRepresentation(customer)));
        return customerRepresentationList;
    }
}
