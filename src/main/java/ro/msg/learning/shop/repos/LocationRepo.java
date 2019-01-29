package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.Location;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    List<Location> findByStocksProductIdAndStocksQuantityGreaterThanEqual(int productId, int quantity);
}
