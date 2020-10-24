package gr.codehub.restapi.resource;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.representation.ProductRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface ProductListResource {

    @Post("json")
    public ProductRepresentation  addProduct(ProductRepresentation product)
            throws BadEntityException;
    @Get("json")
    public List<ProductRepresentation> getProducts() throws NotFoundException;


}
