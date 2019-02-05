package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodels.ProductCategory;

@Repository
public interface CategoryRepo extends JpaRepository<ProductCategory, Integer> {
}
