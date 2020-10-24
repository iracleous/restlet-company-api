package gr.codehub.restapi.representation;


import gr.codehub.restapi.model.Basket;
import gr.codehub.restapi.model.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class BasketRepresentation {

    private Date creationDate;
    private long customerId;
    private long basketId;
    private String uri;


    static public Basket getBasket(BasketRepresentation basketRepresentation){
        Basket basket = new Basket();

        basket.setCreationDate(basketRepresentation.getCreationDate());


        return basket;
    }

    static public BasketRepresentation getBasketRepresentation(Basket basket){
        BasketRepresentation basketRepresentation = new BasketRepresentation();

        basketRepresentation.setCreationDate(basket.getCreationDate());
        if (basket.getCustomer()!=null)
            basketRepresentation.setCustomerId(basket.getCustomer().getId());
        basketRepresentation.setBasketId(basket.getId());

        basketRepresentation.setUri("http://localhost:9000/app/basket/"+basket.getId());
        return basketRepresentation;
    }


}
