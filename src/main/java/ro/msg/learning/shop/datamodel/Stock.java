package ro.msg.learning.shop.datamodel;

import lombok.Data;

@Data
public class Stock {
    private int productId;
    private int locationId;
    private int quantity;
}
