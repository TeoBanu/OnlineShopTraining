package ro.msg.learning.shop.datamodels;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@EdmEntityType
@EdmEntitySet
public class OrderDetail {
    @EmbeddedId
    @EdmKey
    @EdmProperty
    private OrderDetailId id;

    @EdmProperty
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
    @EdmComplexType
    public static class OrderDetailId implements Serializable {
        @NotNull
        @EdmProperty
        private int orderId;

        @NotNull
        @EdmProperty
        private int productId;
    }
}


