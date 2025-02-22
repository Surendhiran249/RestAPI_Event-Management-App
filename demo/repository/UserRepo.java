package event_management.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import event_management.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
    // Custom query to find a user by email
    User findByEmail(String email);
    Page<User> findAll(Pageable pageable);
}
