package ro.msg.learning.shop.datamodels;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    private Date date;

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
