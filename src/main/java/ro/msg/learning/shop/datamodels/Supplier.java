package ro.msg.learning.shop.datamodels;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private int id;
    private String name;
}
