package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodels.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
