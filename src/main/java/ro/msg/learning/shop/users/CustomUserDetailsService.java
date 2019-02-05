package ro.msg.learning.shop.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.datamodels.User;
import ro.msg.learning.shop.repos.RoleRepo;
import ro.msg.learning.shop.repos.UserRepo;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo,
                                    RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("No user with the username " + username + " exists"));
        List<String> roles = roleRepo.findByUsername(username);
        return new CustomUserDetails(user, roles);
    }
}
