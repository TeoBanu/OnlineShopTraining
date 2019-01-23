package ro.msg.learning.shop.datamodel;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;
    private int quantity;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @Embeddable
    @Data
    public static class OrderDetailId implements Serializable {
        @NotNull
        private int orderId;

        @NotNull
        private int productId;
    }
}


