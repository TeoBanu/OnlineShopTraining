package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.datamodels.Order;
import ro.msg.learning.shop.datamodels.Revenue;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("SELECT new ro.msg.learning.shop.datamodels.Revenue(sum(od.quantity * p.price) as sum, o.shippedFrom as location, o.date as date) "+
            "FROM Order o, OrderDetail od, Product p WHERE " +
            "extract(YEAR from date)=:year AND " +
            "extract(MONTH from date)=:month AND " +
            "extract(DAY from date)=:day AND " +
            "od.product.id=p.id AND " +
            "od.order.id=o.id " +
            "GROUP BY o.shippedFrom")
    List<Revenue> findRevenuesForDay(int year, int month, int day);
}
