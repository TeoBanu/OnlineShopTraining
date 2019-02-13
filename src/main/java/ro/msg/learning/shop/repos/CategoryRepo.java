package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.ProductCategory;

public interface CategoryRepo extends JpaRepository<ProductCategory, Integer> {
}
