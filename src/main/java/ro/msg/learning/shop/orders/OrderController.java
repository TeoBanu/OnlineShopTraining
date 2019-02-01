package ro.msg.learning.shop.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.datamodel.Order;
import ro.msg.learning.shop.dtos.OrderDto;

import java.security.Principal;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Order createOrder(Principal principal, @RequestBody OrderDto orderDto) {
        //TODO: get customer from request
        return orderService.create(orderDto);
    }

}
