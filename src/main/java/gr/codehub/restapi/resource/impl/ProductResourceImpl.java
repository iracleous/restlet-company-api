package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.ProductRepresentation;
import gr.codehub.restapi.resource.ProductResource;
import org.restlet.resource.ServerResource;

public class ProductResourceImpl  extends ServerResource implements ProductResource {
    @Override
    public ProductRepresentation getProduct() throws NotFoundException {
        return null;
    }

    @Override
    public void removeProduct() throws NotFoundException {

    }

    @Override
    public ProductRepresentation updateProduct(ProductRepresentation product) throws NotFoundException, BadEntityException {
        return null;
    }
}
