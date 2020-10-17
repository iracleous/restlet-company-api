package gr.codehub.restapi.repository;

import gr.codehub.restapi.model.Product;
import gr.codehub.restapi.repository.lib.Repository;

import javax.persistence.EntityManager;

public class ProductRepository extends Repository<Product,Long> {

    public ProductRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public String getEntityClassName() {
        return Product.class.getName();
    }
}
