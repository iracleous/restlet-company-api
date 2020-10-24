package gr.codehub.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter @Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;
    private String address;
    private Date dob;
    private CustomerCategory category;

    @OneToMany(mappedBy = "customer")
    private List<Basket> baskets = new ArrayList<>();


}