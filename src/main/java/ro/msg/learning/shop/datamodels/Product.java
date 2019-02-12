package ro.msg.learning.shop.datamodels;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@EdmEntityType
public class Product {
    @Id
    @GeneratedValue
    @EdmProperty
    @EdmKey
    private int id;

    @EdmProperty
    private String name;

    @EdmProperty
    private String description;

    @EdmProperty
    private BigDecimal price;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
}
