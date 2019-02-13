package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.datamodels.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
