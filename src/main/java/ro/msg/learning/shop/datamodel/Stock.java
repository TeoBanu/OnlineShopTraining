package ro.msg.learning.shop.datamodel;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
public class Stock {
    @EmbeddedId
    private StockId id;
    private int quantity;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @Embeddable
    @Data
    public static class StockId implements Serializable {
        @NotNull
        private int locationId;

        @NotNull
        private int productId;
    }
}


