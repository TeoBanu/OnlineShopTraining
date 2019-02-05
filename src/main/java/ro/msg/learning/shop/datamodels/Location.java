package ro.msg.learning.shop.datamodels;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String country;
    private String city;
    private String county;
    private String street;
}
