package ro.msg.learning.shop.datamodels;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "id")
    private User user;

    private String name;
}
