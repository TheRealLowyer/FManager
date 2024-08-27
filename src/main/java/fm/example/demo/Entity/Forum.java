package fm.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public void setForumType(ForumType forumType) {
        this.forumType = forumType;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
