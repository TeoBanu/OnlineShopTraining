package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.datamodel.Order;
import ro.msg.learning.shop.datamodel.Stock;
import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.repos.StockRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private StockRepo stockRepo;

    public Order create(OrderDto orderDto) {
//        LocationFinderStrategy strategy = new SingleLocationStrategy();
//        List<LocationProductQuantityDto> locationProductQuantityDtos = strategy.search(orderDto.getProducts());
//        List<Stock> stocksToBeUpdated = locationProductQuantityDtos.stream()
//                .map(locationProductQuantityDto -> {
//                    Stock.StockId stockId = new Stock.StockId(locationProductQuantityDto.getLocation().getId(),
//                            locationProductQuantityDto.getProduct().getId());
//                    return new Stock(stockId, locationProductQuantityDto.getQuantity());
//                })
//                .collect(Collectors.toList());
//        //iterate thorugh strategy result and for each elem, get stock from db, decrease q, save back
//        List<Stock> stocksInDb = stockRepo.findAllById(stocksToBeUpdated.stream().map(Stock::getId).collect(Collectors.toList()));
//        //update stocks
//        stocksInDb.stream().map(stock -> stock.setQuantity(stocksToBeUpdated.))
//        //create order
//        //return order
        return null;
    }
}
