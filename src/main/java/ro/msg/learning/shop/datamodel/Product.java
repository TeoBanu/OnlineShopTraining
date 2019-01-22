package ro.msg.learning.shop.datamodel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private int categoryId;
    private int supplierId;
}
