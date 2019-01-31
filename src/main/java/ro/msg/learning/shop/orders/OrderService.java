package ro.msg.learning.shop.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.datamodel.Location;
import ro.msg.learning.shop.datamodel.Order;
import ro.msg.learning.shop.datamodel.OrderDetail;
import ro.msg.learning.shop.datamodel.Stock;
import ro.msg.learning.shop.dtos.LocationProductQuantityDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.exceptions.ResourceNotFoundException;
import ro.msg.learning.shop.repos.OrderDetailRepo;
import ro.msg.learning.shop.repos.OrderRepo;
import ro.msg.learning.shop.repos.StockRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private StockRepo stockRepo;
    private LocationFinderStrategy strategy;
    private OrderRepo orderRepo;
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    public OrderService(
            LocationFinderStrategy strategy,
            StockRepo stockRepo,
            OrderRepo orderRepo,
            OrderDetailRepo orderDetailRepo) {
        this.strategy = strategy;
        this.stockRepo = stockRepo;
        this.orderRepo = orderRepo;
        this.orderDetailRepo = orderDetailRepo;
    }

    public Order create(OrderDto orderDto) {
        List<LocationProductQuantityDto> locationProductQuantityDtos = strategy.search(orderDto.getProducts());
        List<Stock> stocksWithUpdatedQuantities = locationProductQuantityDtos.stream()
                .map(this::updateStock)
                .collect(Collectors.toList());
        stockRepo.saveAll(stocksWithUpdatedQuantities);

        //create order
        Order order = orderRepo.save(createOrderObject(orderDto, locationProductQuantityDtos.get(0).getLocation()));
        //create order details
        orderDetailRepo.saveAll(createOrderDetailList(order, locationProductQuantityDtos));
        //return order
        return order;
    }

    private List<OrderDetail> createOrderDetailList(Order order, List<LocationProductQuantityDto> locationProductQuantityDtos) {
        return locationProductQuantityDtos.stream()
                .map(locationProductQuantityDto -> createOrderDetailObject(order, locationProductQuantityDto))
                .collect(Collectors.toList());
    }

    private Stock updateStock(LocationProductQuantityDto locationProductQuantityDto) {
        Stock.StockId stockId = new Stock.StockId(locationProductQuantityDto.getLocation().getId(),
                locationProductQuantityDto.getProduct().getId());
        Stock stock = stockRepo.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("stock", "id", stockId.toString()));
        int newQuantity = stock.getQuantity() - locationProductQuantityDto.getQuantity();
        stock.setQuantity(newQuantity);
        return stock;
    }

    private Order createOrderObject(OrderDto orderDto, Location location) {
        Order order = new Order();
        order.setCity(orderDto.getCity());
        order.setCountry(orderDto.getCountry());
        order.setCounty(order.getCounty());
        order.setStreet(orderDto.getStreet());
        order.setShippedFrom(location);
        //TODO: set customer when security & principal
        return null;
    }

    private OrderDetail createOrderDetailObject(Order order, LocationProductQuantityDto locationProductQuantityDto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setQuantity(locationProductQuantityDto.getQuantity());
        orderDetail.setProduct(locationProductQuantityDto.getProduct());
        orderDetail.setId(createOrderDetailId(order.getId(),
                locationProductQuantityDto.getProduct().getId()));
        return orderDetail;
    }

    private OrderDetail.OrderDetailId createOrderDetailId(int orderId, int productId) {
        OrderDetail.OrderDetailId id = new OrderDetail.OrderDetailId();
        id.setOrderId(orderId);
        id.setProductId(productId);
        return id;
    }
}
