package ro.msg.learning.shop.datamodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    //location id
    @ManyToOne
    @JoinColumn(name = "shippedFrom")
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    //delivery address
    private String country;
    private String city;
    private String county;
    private String street;
}
