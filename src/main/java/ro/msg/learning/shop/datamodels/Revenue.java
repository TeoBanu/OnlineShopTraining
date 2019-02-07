package ro.msg.learning.shop.datamodels;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Revenue {
    @Id
    @GeneratedValue
    private int id;

    private Date date;

    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    public Revenue(BigDecimal sum, Location location, Date date) {
        this.date = date;
        this.sum = sum;
        this.location = location;
    }
}
