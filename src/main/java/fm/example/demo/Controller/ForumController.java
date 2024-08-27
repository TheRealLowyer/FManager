package fm.example.demo.Controller;

import fm.example.demo.Entity.Forum;
import fm.example.demo.Entity.Message;
import fm.example.demo.Service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping
    public ResponseEntity<List<Forum>> getAllForums() {
        List<Forum> forums = forumService.getAllForums();
        return ResponseEntity.ok(forums);
    }

    @GetMapping("/{forumId}/messages")
    public ResponseEntity<List<Message>> getMessagesFromForum(@PathVariable String forumId) {
        List<Message> messages = forumService.getMessagesFromForum(forumId);
        if (messages != null && !messages.isEmpty()) {
            return ResponseEntity.ok(messages);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{forumId}/add")
    public ResponseEntity<Forum> addMessageToForum(@PathVariable String forumId, @RequestBody Message message) {
        Forum forum = forumService.addMessageToForum(forumId, message);
        if (forum != null) {
            return ResponseEntity.ok(forum);
        }
        return ResponseEntity.notFound().build();
    }

    // Other methods...
}
