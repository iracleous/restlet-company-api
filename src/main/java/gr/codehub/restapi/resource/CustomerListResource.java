package gr.codehub.restapi.resource;


import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.CustomerRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface CustomerListResource {

    @Post("json")
    public CustomerRepresentation add(CustomerRepresentation customerIn)
            throws BadEntityException;
    @Get("json")
    public List<CustomerRepresentation> getCustomers() throws NotFoundException;


}
