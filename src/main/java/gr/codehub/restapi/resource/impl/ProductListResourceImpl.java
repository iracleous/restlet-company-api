package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Product;
import gr.codehub.restapi.repository.ProductRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.ProductRepresentation;
import gr.codehub.restapi.resource.ProductListResource;
import gr.codehub.restapi.resource.util.ResourceUtils;
import gr.codehub.restapi.security.CustomRole;
import org.restlet.data.Status;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductListResourceImpl extends ServerResource implements ProductListResource {

    public static final Logger LOGGER = Engine.getLogger(ProductResourceImpl.class);


    private ProductRepository productRepository;
    private EntityManager em;

    @Override
    protected void doRelease() {
        em.close();
    }

    @Override
    protected void doInit() {
        LOGGER.info("Initialising product resource starts");
        try {
            em = JpaUtil.getEntityManager();
            productRepository = new ProductRepository(em);
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }
        LOGGER.info("Initialising product resource ends");
    }

    public List<ProductRepresentation> getProducts() throws NotFoundException {
        LOGGER.finer("Select all products.");
        // Check authorization
        ResourceUtils.checkRole(this,
                new ArrayList<>(Arrays.asList(CustomRole.ROLE_ADMIN.getRoleName())));

        try {

            List<Product> products =
                    productRepository.findAll();
            List<ProductRepresentation> result = new ArrayList<>();

            products.forEach(product ->
                    result.add(ProductRepresentation.getProductRepresentation(product)));
            return result;
        } catch (Exception e) {
            throw new NotFoundException("products not found");
        }
    }


    @Override
    public ProductRepresentation addProduct(ProductRepresentation productRepresentationIn) throws BadEntityException {

        LOGGER.finer("Add a new product.");

        // Check authorization
        ResourceUtils.checkRole(this,
                new ArrayList<>(Arrays.asList(CustomRole.ROLE_ADMIN.getRoleName())));
        LOGGER.finer("User allowed to add a product.");

        // Check entity
        if (productRepresentationIn == null) throw new BadEntityException("null productRepresentationIn");

        LOGGER.finer("Product checked");

        try {
            Product productIn = ProductRepresentation.getProduct(productRepresentationIn);
            Optional<Product> productOut = productRepository.save(productIn);
            Product product ;
            if (productOut.isPresent())
                product = productOut.get();
            else
                throw new BadEntityException(" Product has not been created");

            ProductRepresentation result = ProductRepresentation.getProductRepresentation(product);

            getResponse().setLocationRef( "http://localhost:9000/app/product/" + product.getId());
            getResponse().setStatus(Status.SUCCESS_CREATED);
            LOGGER.finer("Product successfully added.");
            return result;
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Error when adding a product", ex);
            throw new ResourceException(ex);
        }
    }
}
