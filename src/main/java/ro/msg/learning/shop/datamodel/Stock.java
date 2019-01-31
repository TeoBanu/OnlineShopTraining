package ro.msg.learning.shop.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Stock {
    @EmbeddedId
    @JsonUnwrapped
    private StockId id;
    private int quantity;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    @JsonIgnore
    private Location location;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonPropertyOrder({"locationId", "productId"})
    public static class StockId implements Serializable {
        @NotNull
        private int locationId;

        @NotNull
        private int productId;
    }

}


