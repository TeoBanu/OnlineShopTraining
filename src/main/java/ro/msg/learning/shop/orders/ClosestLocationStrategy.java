package ro.msg.learning.shop.orders;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
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
    private static final String KEY_KEY = "key";
    private static final String KEY_VALUE = "AIzaSyBPtBPSLKNAwOfu3zq48XaIVyLlscgdz5M";
    private static final String UNITS_KEY = "units";
    private static final String UNITS_VALUE = "metric";
    private static final String ORIGINS_KEY = "origins";
    private static final String DESTINATIONS_KEY = "destinations";
    private static final String GOOGLE_DISTANCE_ENDPOINT = "https://maps.googleapis.com/maps/api/distancematrix/json";

    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;
    private final StockRepo stockRepo;
    private final RestTemplate restTemplate;

    public ClosestLocationStrategy(LocationRepo locationRepo,
                                  ProductRepo productRepo,
                                  StockRepo stockRepo,
                                   RestTemplate restTemplate) {
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
        this.restTemplate = restTemplate;
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

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GOOGLE_DISTANCE_ENDPOINT)
                .queryParam(UNITS_KEY, UNITS_VALUE)
                .queryParam(ORIGINS_KEY, customerLocationString)
                .queryParam(DESTINATIONS_KEY, productLocationString)
                .queryParam(KEY_KEY, KEY_VALUE);

        return sendPost(builder.toUriString()).getBody().getRows().get(0).getElements().get(0).getDistance().getValue();

    }

    private String getCommaSeparatedLocationString(Location location) {
        return
                location.getStreet() + "," +
                location.getCity() + "," +
                location.getCounty() + "," +
                location.getCountry();
    }

    private ResponseEntity<GoogleDistance> sendPost(String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);

        return restTemplate.exchange(uri,
                HttpMethod.GET,
                request,
                GoogleDistance.class);

    }

    private Location createCustomerLocation(String street, String country, String county, String city) {
        Location location = new Location();
        location.setStreet(street);
        location.setCity(city);
        location.setCountry(country);
        location.setCounty(county);
        return location;
    }

}
