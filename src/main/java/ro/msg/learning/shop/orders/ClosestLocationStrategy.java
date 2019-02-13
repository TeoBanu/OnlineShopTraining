package ro.msg.learning.shop.orders;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import ro.msg.learning.shop.datamodels.Address;
import ro.msg.learning.shop.datamodels.Location;
import ro.msg.learning.shop.datamodels.Product;
import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.dtos.OrderProductDto;
import ro.msg.learning.shop.exceptions.ResourceNotFoundException;
import ro.msg.learning.shop.repos.LocationRepo;
import ro.msg.learning.shop.repos.ProductRepo;
import ro.msg.learning.shop.repos.StockRepo;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClosestLocationStrategy implements LocationFinderStrategy {

    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;
    private final StockRepo stockRepo;
    private final RestTemplate restTemplate;
    private final GoogleConfigurationProperties config;

    public ClosestLocationStrategy(LocationRepo locationRepo,
                                   ProductRepo productRepo,
                                   StockRepo stockRepo,
                                   RestTemplate restTemplate,
                                   GoogleConfigurationProperties config) {
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    public List<LocationProductQuantityDto> search(OrderDto orderDto) {
        List<OrderProductDto> productQuantities = orderDto.getProducts();
        Map<Integer, Integer> productQuantityMap = productQuantities.stream().collect(Collectors.toMap(OrderProductDto::getId, OrderProductDto::getQuantity));
        List<Integer> satisfactoryLocationIds = stockRepo.selectSatisfactoryLocations(productQuantities);
        if (satisfactoryLocationIds == null || satisfactoryLocationIds.isEmpty()) {
            throw new ResourceNotFoundException("location", "stock", "enough");
        }

        List<Location> satisfactoryLocations = locationRepo.findAllById(satisfactoryLocationIds);
        Location location;
        if(satisfactoryLocations.size() == 1) {
            location = satisfactoryLocations.get(0);
        } else {
            //find the closest
            Location customerLocation = createCustomerLocation(orderDto.getStreet(),
                    orderDto.getCountry(),
                    orderDto.getCounty(),
                    orderDto.getCity());
            location = Collections.min(satisfactoryLocations, Comparator.comparing(l -> getDistance(customerLocation,l)));
        }

        List<Integer> productIds = productQuantities.stream().map(OrderProductDto::getId).collect(Collectors.toList());
        Map<Product, Integer> fullProductQuantityMap = productRepo.findAllById(productIds).stream()
                .collect(Collectors.toMap(Function.identity(), product -> productQuantityMap.get(product.getId())));

        List<LocationProductQuantityDto> locationProductQuantityDtos = new ArrayList<>();
        fullProductQuantityMap.forEach((product, quantity) -> locationProductQuantityDtos.add(new LocationProductQuantityDto(location, product, quantity)));

        return locationProductQuantityDtos;
    }

    private long getDistance(Location customerLocation, Location productLocation) {

        String customerLocationString = getCommaSeparatedLocationString(customerLocation);
        String productLocationString = getCommaSeparatedLocationString(productLocation);

        return sendPost(customerLocationString, productLocationString).getBody().getRows().get(0).getElements().get(0).getDistance().getValue();

    }

    private String getCommaSeparatedLocationString(Location location) {
        return
                location.getAddress().getStreet() + "," +
                location.getAddress().getCity() + "," +
                location.getAddress().getCounty() + "," +
                location.getAddress().getCountry();
    }

    private ResponseEntity<GoogleDistance> sendPost(String customerLocation, String productLocation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);

        UriTemplate uriTemplate = new UriTemplate(config.getUri());
        String uri = uriTemplate.expand(customerLocation, productLocation, config.getKey()).toString();

        return restTemplate.exchange(uri,
                HttpMethod.GET,
                request,
                GoogleDistance.class);

    }

    private Location createCustomerLocation(String street, String country, String county, String city) {
        Location location = new Location();
        Address address = new Address(country, city, county, street);

        location.setAddress(address);

        return location;
    }

}
