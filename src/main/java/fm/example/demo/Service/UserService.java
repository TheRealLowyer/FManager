package fm.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import fm.example.demo.Entity.User;
import fm.example.demo.Repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Method to create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Method to find a user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to find a user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public String login(String username, String password) {
        Optional<User> userOptional = findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Generate and return JWT token (if applicable)
                return "Login successful!";
            }
        }
        throw new RuntimeException("Invalid username or password!");
    }

    // Other methods like updateUser, deleteUser, etc.
}
