package ro.msg.learning.shop.orders;

import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.dtos.OrderProductDto;

import java.util.List;

public interface LocationFinderStrategy {
    List<LocationProductQuantityDto> search(List<OrderProductDto> productQuantityMap);
}
