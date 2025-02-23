package event_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import event_management.demo.entity.User;
import event_management.demo.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepo.saveAll(users);
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User updateUser(Long id, User updatedUser) {
        if(userRepo.existsById(id)){
            updatedUser.setId(id);
            return userRepo.save(updatedUser);
        }
        return null;
    }

    public String deleteUser(Long id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return "User deleted successfully";
        }
        return "User not found";
    }

    public Page<User> getUsersPaginated(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public List<User> getUsersSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return userRepo.findAll(sort);
    }

    public List<User> searchUsers(String keyword) {
        return userRepo.searchUsers(keyword);
    }

}
