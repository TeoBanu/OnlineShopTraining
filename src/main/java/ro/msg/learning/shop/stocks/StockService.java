package ro.msg.learning.shop.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.datamodel.Stock;
import ro.msg.learning.shop.repos.StockRepo;

import java.util.List;

@Service
public class StockService {

    private final StockRepo stockRepo;

    @Autowired
    public StockService(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }

    public List<Stock> exportForLocationWithId(int locationId) {
        return stockRepo.findByIdLocationId(locationId);
    }
}
