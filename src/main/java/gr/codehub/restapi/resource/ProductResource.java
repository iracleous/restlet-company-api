package gr.codehub.restapi.resource;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.representation.CustomerRepresentation;
import gr.codehub.restapi.representation.ProductRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ProductResource {


        @Get("json")
        public ProductRepresentation getProduct() throws NotFoundException;



        @Delete
        public void removeProduct() throws NotFoundException;

        @Put("json")
        public ProductRepresentation updateProduct(ProductRepresentation product)
                throws NotFoundException, BadEntityException;


    }
