package ro.msg.learning.shop.datamodels;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
    }
}
