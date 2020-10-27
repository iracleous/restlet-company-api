package gr.codehub.restapi.resource.impl;

import gr.codehub.restapi.exceptions.BadEntityException;
import gr.codehub.restapi.exceptions.NotFoundException;
import gr.codehub.restapi.model.Product;
import gr.codehub.restapi.repository.ProductRepository;
import gr.codehub.restapi.repository.util.JpaUtil;
import gr.codehub.restapi.representation.ProductRepresentation;
import gr.codehub.restapi.resource.ProductResource;
import gr.codehub.restapi.resource.util.ResourceUtils;
import gr.codehub.restapi.security.CustomRole;
import org.restlet.engine.Engine;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductResourceImpl extends ServerResource implements ProductResource {

    public static final Logger LOGGER = Engine.getLogger(ProductResourceImpl.class);

    private long id;
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
            id = Long.parseLong(getAttribute("id"));
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }

        LOGGER.info("Initialising product resource ends");
    }

    @Override
    public ProductRepresentation getProduct() throws NotFoundException {
        LOGGER.info("Retrieve a product");
        // Check authorization
        ResourceUtils.checkRole(this,
                new ArrayList<>(Arrays.asList(CustomRole.ROLE_ADMIN.getRoleName())));
        // Initialize the persistence layer.
        ProductRepository productRepository = new ProductRepository(em);
        Product product;
        try {
            Optional<Product> oproduct = productRepository.findById(id);
            setExisting(oproduct.isPresent());
            if (!isExisting()) {
                LOGGER.config("product id does not exist:" + id);

                throw new NotFoundException("No product with  : " + id);

            } else {
                product = oproduct.get();
                LOGGER.finer("Product successfully retrieved");
                return ProductRepresentation.getProductRepresentation(product);
            }
        } catch (Exception ex) {
            throw new NotFoundException("No product with  : " + id);
        }
    }

    @Override
    public void removeProduct() throws NotFoundException {
        LOGGER.info("Removal of product");
        // Check authorization
        ResourceUtils.checkRole(this,
                new ArrayList<>(Arrays.asList(CustomRole.ROLE_ADMIN.getRoleName())));
        LOGGER.finer("User allowed to remove a product.");

        try {

            // Delete company in DB: return true if deleted
            boolean isDeleted = productRepository.deleteById(id);

            // If product has not been deleted: if not it means that the id must
            // be wrong
            if (!isDeleted) {
                LOGGER.config("Product id does not exist");
                throw new NotFoundException(
                        "Product with the following identifier does not exist:"
                                + id);
            }
            LOGGER.finer("Product successfully removed.");

        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Error when removing a product", ex);
            throw new ResourceException(ex);
        }
    }

    @Override
    public ProductRepresentation updateProduct(ProductRepresentation productReprIn)
            throws NotFoundException, BadEntityException {
        LOGGER.finer("Update a product.");

        // Check authorization
        ResourceUtils.checkRole(this,
                new ArrayList<String>(Arrays.asList(CustomRole.ROLE_ADMIN.getRoleName())));
        LOGGER.finer("User allowed to update a product.");

        // Check entity
        if (productReprIn == null) throw new BadEntityException("null productRepresentationIn");

        LOGGER.finer("productReprIn checked");

        try {
            Optional<Product> productOut;
            Optional<Product> oProduct = productRepository.findById(id);
            setExisting(oProduct.isPresent());

            // If product exists, we update it.
            if (isExisting()) {
                LOGGER.finer("Update product.");
                Product   productIn    = oProduct.get();
                productIn.setName(productReprIn.getName());
                productIn.setPrice(productReprIn.getPrice());
                productIn.setInventoryQuantity(productReprIn.getInventoryQuantity());
                // Update product in DB and retrieve the new one.
                productOut = productRepository.save(productIn);

                // Check if retrieved product is not null : if it is null
                // it means that the id is wrong.
                if (!productOut.isPresent()) {
                    LOGGER.finer("Product does not exist.");
                    throw new NotFoundException(
                            "Product with the following id does not exist: "
                                    + id);
                }
            } else {
                LOGGER.finer("Resource does not exist.");
                throw new NotFoundException(
                        "Product with the following id does not exist: " + id);
            }

            LOGGER.finer("Product successfully updated.");
            return ProductRepresentation.getProductRepresentation(productOut.get());

        } catch (Exception ex) {
            throw new ResourceException(ex);
        }
    }
}
