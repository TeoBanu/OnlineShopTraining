package ro.msg.learning.shop.datamodel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
}
