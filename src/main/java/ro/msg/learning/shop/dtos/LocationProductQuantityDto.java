package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.datamodel.Location;
import ro.msg.learning.shop.datamodel.Product;

@Data
@AllArgsConstructor
public class LocationProductQuantityDto {
    private Location location;
    private Product product;
    private int quantity;
}
