package ro.msg.learning.shop.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.datamodel.Stock;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = "/export/{locationId}", produces = "text/csv")
    public List<Stock> exportStocksForLocation(@PathVariable("locationId") int locationId) {
        return stockService.exportForLocationWithId(locationId);
    }
}
