package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.Revenue;

public interface RevenueRepo extends JpaRepository<Revenue, Integer> {
}
