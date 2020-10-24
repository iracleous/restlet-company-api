package gr.codehub.restapi.resource;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.BasketRepresentation;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.representation.ProductRepresentation;
import org.restlet.resource.*;

public interface BasketResource {

    @Get("json")
    public BasketRepresentation getBasket() throws NotFoundException;

    @Delete
    public void removeBasket() throws NotFoundException;

    @Put("json")
    public BasketRepresentation updateBasket(ProductRepresentation product)
            throws NotFoundException, BadEntityException;

    @Post("json")
    public BasketRepresentation createBasket()
            throws NotFoundException, BadEntityException;


    @Patch("json")
    public BasketRepresentation assignCustomer(BasketRepresentation basketRepresentation)
            throws NotFoundException, BadEntityException;
}
