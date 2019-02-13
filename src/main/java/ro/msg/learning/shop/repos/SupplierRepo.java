package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
}
