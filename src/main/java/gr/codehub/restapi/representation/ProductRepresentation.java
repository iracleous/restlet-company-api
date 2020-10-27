package gr.codehub.restapi.representation;


import gr.codehub.restapi.model.Customer;
import gr.codehub.restapi.model.Product;
import lombok.Data;

@Data
public class ProductRepresentation {

    private String name;
    private double price;
    private int  inventoryQuantity;

    private String uri;


    static public Product getProduct(ProductRepresentation productRepresentation){
        Product product = new Product();

        product.setName( productRepresentation.getName() );
        product.setPrice(  productRepresentation.getPrice() );
        product.setInventoryQuantity(  productRepresentation.getInventoryQuantity() );
         return product;
    }

    static public ProductRepresentation getProductRepresentation(Product product){
        ProductRepresentation productRepresentation = new ProductRepresentation();

        productRepresentation.setName(product.getName());
        productRepresentation.setPrice(product.getPrice());
        productRepresentation.setInventoryQuantity(product.getInventoryQuantity());

        productRepresentation.setUri("http://localhost:9000/app/product/"+product.getId());
        return productRepresentation;
    }



}
