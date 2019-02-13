package ro.msg.learning.shop.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;
    private String city;
    private String county;
    private String street;
}
