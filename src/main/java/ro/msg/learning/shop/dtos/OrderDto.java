package ro.msg.learning.shop.dtos;

import lombok.Data;
import ro.msg.learning.shop.datamodels.Customer;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Date timestamp;
    private String country;
    private String city;
    private String county;
    private String street;
    private List<OrderProductDto> products;
    private Customer customer;
}
