package ro.msg.learning.shop.datamodel;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
}
