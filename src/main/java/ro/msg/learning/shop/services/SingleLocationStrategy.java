package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.datamodel.Location;
import ro.msg.learning.shop.datamodel.Product;
import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.repos.LocationRepo;
import ro.msg.learning.shop.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SingleLocationStrategy implements LocationFinderStrategy {

    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<LocationProductQuantityDto> search(Map<Integer, Integer> productQuantityMap) {
        Set<Integer> productIds = productQuantityMap.keySet();

        Map<Product, Integer> products = productRepo.findAllById(productIds).stream()
                .collect(Collectors.toMap(Function.identity(), product -> productQuantityMap.get(product.getId())));
        List<Location> locations = new ArrayList<>();
        products.forEach((product, quantity) ->
                locations.addAll(locationRepo.findByStocksProductIdAndStocksQuantityGreaterThanEqual(product.getId(), quantity))
        );
        Map<Location, Long> locationCount = locations.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long numberOfOrderedProducts = (long) products.size();
        Location satisfactoryLocation = locationCount.entrySet().stream()
                .filter(entry -> entry.getValue() == numberOfOrderedProducts)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No location with enough stock"));
        List<LocationProductQuantityDto> locationProductQuantityDtos = new ArrayList<>();
        products.forEach((product, quantity) -> locationProductQuantityDtos.add(new LocationProductQuantityDto(satisfactoryLocation, product, quantity)));

        return locationProductQuantityDtos;
    }
}
