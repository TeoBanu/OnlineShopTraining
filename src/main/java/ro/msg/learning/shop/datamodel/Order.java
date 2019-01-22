package ro.msg.learning.shop.datamodel;

import lombok.Data;

@Data
public class Order {
    private int id;
    //location id
    private int shippedFrom;
    private int customerId;
    //delivery address
    private String country;
    private String city;
    private String county;
    private String street;
}
