package ro.msg.learning.shop.datamodels;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Revenue {
    @Id
    @GeneratedValue
    private int id;

    private Date date;

    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;
}
