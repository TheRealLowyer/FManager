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
import java.util.Map;
import java.util.HashMap;


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


        User newUser = userService.signUpUser(user);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userService.findByUsername(loginRequest.getUsername());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
                if (passwordMatches) {
                    String token = jwtUtil.generateToken(user.getUsername());

                    LoginResponse loginResponse = new LoginResponse(
                            user,
                            token
                    );
                    return ResponseEntity.ok(loginResponse);
                } else {
                    return ResponseEntity.badRequest().body("Invalid password!");
                }
            } else {
                return ResponseEntity.badRequest().body("Invalid username!");
            }
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
                // Fetch user details from the database or user service
                Optional<User> optionall = userService.findByUsername(username); // Replace with your actual method to get user details

                if (optionall.isPresent()) {
                    User user = optionall.get();
                    // Construct the response with all user details
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Token is valid");
                    response.put("id", user.getId());
                    response.put("username", user.getUsername());
                    response.put("email", user.getEmail());
                    response.put("role", user.getRole());
                    response.put("country", user.getCountry());
                    response.put("base64Photo",user.getBase64Photo());
                    // Add other fields as needed

                    // Include team details but exclude players
                    Map<String, Object> teamDetails = new HashMap<>();
                    teamDetails.put("teamId", user.getTeam().getTeamId());
                    teamDetails.put("teamName", user.getTeam().getTeamName());
                    // Add other team fields as needed, excluding players
                    response.put("team", teamDetails);

                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid token"));
    }



    // Other user-related endpoints (like get all users, get user by ID, etc.)
}
