package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.Location;

public interface LocationRepo extends JpaRepository<Location, Integer> {
}
