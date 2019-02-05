package ro.msg.learning.shop.datamodels;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
}
