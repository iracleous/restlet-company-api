package gr.codehub.restapi.representation;

import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.model.CustomerCategory;
import lombok.Data;


import java.util.Date;


@Data
public class CustomerRepresentation {


    private String name;
    private String address;
    private Date dob;
    private CustomerCategory category;

    private String uri;

    static public Customer getCustomer(CustomerRepresentation customerRepresentation){
        Customer customer = new Customer();

        customer.setAddress(customerRepresentation.getAddress());
        customer.setName(customerRepresentation.getName());
        customer.setDob(customerRepresentation.getDob());
        customer.setCategory(customerRepresentation.getCategory());

        return customer;
    }

    static public CustomerRepresentation getCustomerRepresentation(Customer customer){
        CustomerRepresentation customerRepresentation = new CustomerRepresentation();

        customerRepresentation.setAddress(customer.getAddress());
        customerRepresentation.setName(customer.getName());
        customerRepresentation.setDob(customer.getDob());
        customerRepresentation.setCategory(customer.getCategory());

        return customerRepresentation;
    }

}
