package ro.msg.learning.shop.datamodel;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Stock> stocks;
}
