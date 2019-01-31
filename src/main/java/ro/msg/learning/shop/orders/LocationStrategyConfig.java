package ro.msg.learning.shop.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repos.LocationRepo;
import ro.msg.learning.shop.repos.ProductRepo;

@Configuration
public class LocationStrategyConfig {

    private final LocationStrategyType type;
    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;

    @Autowired
    public LocationStrategyConfig(@Value("${location.strategy}") LocationStrategyType type,
                                  LocationRepo locationRepo,
                                  ProductRepo productRepo) {
        this.type = type;
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
    }

    @Bean
    public LocationFinderStrategy getLocationFinderStrategy() {
        switch (type) {
            case SINGLE:
                return new SingleLocationStrategy(locationRepo, productRepo);
            default:
                throw new IllegalArgumentException("No location strategy implementation for given type");
        }
    }
}
