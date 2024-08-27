package fm.example.demo.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "messages")
public class Message {

    @Id
    private String id;

    private String username;
    private Role role;  // Role (admin or user)
    private Date date;
    private String time;
    private String message;

    @ManyToOne
    @JsonIgnoreProperties({"forumName", "forumType", "messages"})
    private Forum forum; // Reference to the Forum
    public enum Role {
        ADMIN, USER
    }

}
