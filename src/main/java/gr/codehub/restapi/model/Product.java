package gr.codehub.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;
    private double price;
    private int  inventoryQuantity;

    @OneToMany(mappedBy = "product")
    private List<BasketProduct> basketProducts = new ArrayList<>();
}
