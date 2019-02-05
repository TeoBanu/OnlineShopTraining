package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.datamodels.Location;
import ro.msg.learning.shop.datamodels.Product;

@Data
@AllArgsConstructor
public class LocationProductQuantityDto {
    private Location location;
    private Product product;
    private int quantity;
}
