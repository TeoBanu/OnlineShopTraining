package ro.msg.learning.shop.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.repos.LocationRepo;
import ro.msg.learning.shop.repos.ProductRepo;
import ro.msg.learning.shop.repos.StockRepo;

@Configuration
public class LocationStrategyConfig {

    private final LocationStrategyType type;
    private final StockRepo stockRepo;
    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public LocationStrategyConfig(@Value("${location.strategy}") LocationStrategyType type,
                                  RestTemplate restTemplate,
                                  LocationRepo locationRepo,
                                  ProductRepo productRepo,
                                  StockRepo stockRepo) {
        this.type = type;
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
        this.restTemplate = restTemplate;
    }

    @Bean
    public LocationFinderStrategy getLocationFinderStrategy() {
        switch (type) {
            case SINGLE:
                return new SingleLocationStrategy(locationRepo, productRepo, stockRepo);
            case CLOSEST:
                return new ClosestLocationStrategy(locationRepo, productRepo, stockRepo, restTemplate);
            default:
                throw new IllegalArgumentException("No location strategy implementation for given type");
        }
    }
}
