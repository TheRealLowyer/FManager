package fm.example.demo.Model;

import fm.example.demo.Entity.User;
import fm.example.demo.Entity.Team;

public class LoginResponse {
    private Long id;  // User ID
    private String username;
    private String email;  // Email address
    private String base64Photo;  // Base64 encoded photo
    private User.Role role;  // User role
    private String country;  // Country
    private String token;  // JWT Token
    private Team team;  // Associated Team

    public LoginResponse(User user, String token) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.base64Photo = user.getBase64Photo();
        this.role = user.getRole();
        this.country = user.getCountry();
        this.team = user.getTeam();  // Assuming Team is a serializable object
        this.token = token;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
