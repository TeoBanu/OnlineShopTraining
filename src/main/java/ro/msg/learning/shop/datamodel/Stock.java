package ro.msg.learning.shop.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Stock {
    @EmbeddedId
    @EqualsAndHashCode.Include
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

    public Stock(StockId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    public static class StockId implements Serializable {
        @NotNull
        private int locationId;

        @NotNull
        private int productId;
    }
}


