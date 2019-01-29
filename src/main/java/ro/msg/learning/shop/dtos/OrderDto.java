package ro.msg.learning.shop.dtos;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class OrderDto {
    private Date timestamp;
    private String country;
    private String city;
    private String county;
    private String street;
    private Map<Integer, Integer> products;
}
