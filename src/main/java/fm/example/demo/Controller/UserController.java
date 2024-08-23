package fm.example.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import fm.example.demo.Service.UserService;
import fm.example.demo.Entity.User;
import fm.example.demo.Model.LoginRequest;
import fm.example.demo.Model.LoginResponse;
import fm.example.demo.Security.JwtUtil;


import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userService.createUser(user);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userService.findByUsername(loginRequest.getUsername());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    String token = jwtUtil.generateToken(user.getUsername());

                    LoginResponse loginResponse = new LoginResponse(
                            user.getUsername(),
                            user.getRole(),
                            user.getBase64Photo(),
                            token
                    );

                    return ResponseEntity.ok(loginResponse);
                }
            }
            return ResponseEntity.badRequest().body("Invalid username or password!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
        }
        try {
            String username = jwtUtil.extractUsername(token);
            if (username != null && jwtUtil.validateToken(token, username)) {
                return ResponseEntity.ok("Token is valid");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }


    // Other user-related endpoints (like get all users, get user by ID, etc.)
}
