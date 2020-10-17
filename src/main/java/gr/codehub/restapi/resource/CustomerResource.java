package gr.codehub.restapi.resource;


import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.CustomerRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface CustomerResource {

    @Get("json")
    public CustomerRepresentation getCustomer() throws NotFoundException;

    @Delete
    public void remove() throws NotFoundException;

    @Put("json")
    public CustomerRepresentation update(CustomerRepresentation customerReprIn)
            throws NotFoundException, BadEntityException;


}
