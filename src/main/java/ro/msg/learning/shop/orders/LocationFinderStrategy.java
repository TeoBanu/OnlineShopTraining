package ro.msg.learning.shop.orders;

import ro.msg.learning.shop.dtos.LocationProductQuantityDto;

import java.util.List;
import java.util.Map;

public interface LocationFinderStrategy {
    List<LocationProductQuantityDto> search(Map<Integer, Integer> productQuantityMap);
}
