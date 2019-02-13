package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, OrderDetail.OrderDetailId> {
}
