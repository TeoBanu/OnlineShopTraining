package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
