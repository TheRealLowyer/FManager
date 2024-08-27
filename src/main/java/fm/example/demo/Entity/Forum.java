package fm.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "forums")
public class Forum {

    @Id

    private String id;

    private String forumName;
    private ForumType forumType;

    @OneToMany(mappedBy = "forum")
    private List<Message> messages = new ArrayList<>();

    // Getters and Setters
    public enum ForumType {
        GENERAL,
        COMMUNITY
    }

}
