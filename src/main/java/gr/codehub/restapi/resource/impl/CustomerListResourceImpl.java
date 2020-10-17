package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.resource.CustomerListResource;
import gr.codehub.restapi.resource.CustomerResource;
import org.restlet.resource.ServerResource;

import java.util.List;

public class CustomerListResourceImpl extends ServerResource implements CustomerListResource {
    @Override
    public CustomerRepresentation add(CustomerRepresentation customerIn) throws BadEntityException {
       throw new BadEntityException("unimplemented");
    }

    @Override
    public List<CustomerRepresentation> getCustomers() throws NotFoundException {
        throw new NotFoundException("unimplemented");
    }
}
