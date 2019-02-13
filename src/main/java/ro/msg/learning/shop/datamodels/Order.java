package ro.msg.learning.shop.datamodels;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
@EdmEntityType
@EdmEntitySet
public class Order {
    @Id
    @GeneratedValue
    @EdmProperty
    @EdmKey
    private int id;

    @EdmProperty
    private Date date;

    //location id
    @ManyToOne
    @JoinColumn(name = "shippedFrom")
    @EdmNavigationProperty
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @EdmNavigationProperty
    private Customer customer;

    //delivery address
    @Embedded
    private Address deliveryAddress;
}
