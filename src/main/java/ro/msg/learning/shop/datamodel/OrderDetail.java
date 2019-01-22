package ro.msg.learning.shop.datamodel;

import lombok.Data;

@Data
public class OrderDetail {
    private int orderId;
    private int productId;
    private int quantity;
}
