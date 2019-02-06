package ro.msg.learning.shop.orders;

import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.dtos.OrderDto;

import java.util.List;

public interface LocationFinderStrategy {
    List<LocationProductQuantityDto> search(OrderDto orderDto);
}
