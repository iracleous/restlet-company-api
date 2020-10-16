package gr.codehub.restapi.representation;


import lombok.Data;

@Data
public class ProductRepresentation {

    private String name;
    private double price;
    private int  inventoryQuantity;

    private String uri;

}
