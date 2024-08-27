package fm.example.demo.Repo;

import fm.example.demo.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    // Custom query to find messages by forum ID
    List<Message> findByForumId(String forumId);
}
