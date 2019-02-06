package ro.msg.learning.shop.datamodels;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

    private String email;

    @OneToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;


    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
        customer = user.getCustomer();
    }
}
