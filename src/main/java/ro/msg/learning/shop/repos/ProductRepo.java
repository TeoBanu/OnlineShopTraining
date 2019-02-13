package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
