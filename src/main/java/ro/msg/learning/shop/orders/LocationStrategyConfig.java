package ro.msg.learning.shop.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repos.LocationRepo;
import ro.msg.learning.shop.repos.ProductRepo;
import ro.msg.learning.shop.repos.StockRepo;

@Configuration
public class LocationStrategyConfig {

    private final LocationStrategyType type;
    private final StockRepo stockRepo;
    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;

    @Autowired
    public LocationStrategyConfig(@Value("${location.strategy}") LocationStrategyType type,
                                  LocationRepo locationRepo,
                                  ProductRepo productRepo,
                                  StockRepo stockRepo) {
        this.type = type;
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
    }

    @Bean
    public LocationFinderStrategy getLocationFinderStrategy() {
        switch (type) {
            case SINGLE:
                return new SingleLocationStrategy(locationRepo, productRepo, stockRepo);
            default:
                throw new IllegalArgumentException("No location strategy implementation for given type");
        }
    }
}
