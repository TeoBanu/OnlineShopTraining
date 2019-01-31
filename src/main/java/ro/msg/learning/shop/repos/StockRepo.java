package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.Stock;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, Stock.StockId> {
    List<Stock> findByIdLocationId(int locationId);
}
