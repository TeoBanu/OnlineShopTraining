package ro.msg.learning.shop.orders;

import ro.msg.learning.shop.datamodel.Location;
import ro.msg.learning.shop.datamodel.Product;
import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.dtos.OrderProductDto;
import ro.msg.learning.shop.exceptions.ResourceNotFoundException;
import ro.msg.learning.shop.repos.LocationRepo;
import ro.msg.learning.shop.repos.ProductRepo;
import ro.msg.learning.shop.repos.StockRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SingleLocationStrategy implements LocationFinderStrategy {

    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;
    private final StockRepo stockRepo;

    public SingleLocationStrategy(LocationRepo locationRepo,
                                  ProductRepo productRepo,
                                  StockRepo stockRepo) {
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
    }

    @Override
    public List<LocationProductQuantityDto> search(List<OrderProductDto> productQuantities) {
        Map<Integer, Integer> productQuantityMap = productQuantities.stream().collect(Collectors.toMap(OrderProductDto::getId, OrderProductDto::getQuantity));
        List<Integer> satisfactoryLocationIds = stockRepo.selectSatisfactoryLocations(productQuantities);
        if (satisfactoryLocationIds == null || satisfactoryLocationIds.isEmpty()) {
            throw new ResourceNotFoundException("location", "stock", "enough");
        }
        int satisfactoryLocationId = satisfactoryLocationIds.get(0);
        Location location = locationRepo.findById(satisfactoryLocationId)
                .orElseThrow(() -> new ResourceNotFoundException("location", "id", String.valueOf(satisfactoryLocationId)));
        List<Integer> productIds = productQuantities.stream().map(OrderProductDto::getId).collect(Collectors.toList());
        Map<Product, Integer> fullProductQuantityMap = productRepo.findAllById(productIds).stream()
                .collect(Collectors.toMap(Function.identity(), product -> productQuantityMap.get(product.getId())));

        List<LocationProductQuantityDto> locationProductQuantityDtos = new ArrayList<>();
        fullProductQuantityMap.forEach((product, quantity) -> locationProductQuantityDtos.add(new LocationProductQuantityDto(location, product, quantity)));

        return locationProductQuantityDtos;
    }
}
