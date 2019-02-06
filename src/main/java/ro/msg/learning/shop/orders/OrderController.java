package ro.msg.learning.shop.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.datamodels.Order;
import ro.msg.learning.shop.datamodels.User;
import ro.msg.learning.shop.dtos.OrderDto;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Order createOrder(AbstractAuthenticationToken principal, @RequestBody OrderDto orderDto) {
        Object principalObj = principal.getPrincipal();
        if (principalObj instanceof User) {
            User user = (User) principalObj;
            orderDto.setCustomer(user.getCustomer());
            orderDto.setTimestamp(new Date());
            return orderService.create(orderDto);
        }
        throw new UsernameNotFoundException("No user principal");
    }

}
