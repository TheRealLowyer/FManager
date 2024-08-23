package fm.example.demo.Model;

import fm.example.demo.Entity.User;

public class LoginResponse {
    private String username;
    private User.Role role;  // Change type to User.Role
    private String base64Photo;
    private String token;

    public LoginResponse(String username, User.Role role, String base64Photo, String token) {
        this.username = username;
        this.role = role;
        this.base64Photo = base64Photo;
        this.token = token;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
