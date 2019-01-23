package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
