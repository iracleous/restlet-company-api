package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.ProductRepresentation;
import gr.codehub.restapi.resource.ProductListResource;
import org.restlet.resource.ServerResource;

import java.util.List;

public class ProductListResourceImpl extends ServerResource implements ProductListResource {
    @Override
    public ProductRepresentation addProduct(ProductRepresentation product) throws BadEntityException {
        return null;
    }

    @Override
    public List<ProductRepresentation> getProducts() throws NotFoundException {
        return null;
    }
}
