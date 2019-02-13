package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.datamodels.Role;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("select r.name from Role r, User u where r.user.id=u.id and u.username=:username")
    List<String> findByUsername(String username);
}
