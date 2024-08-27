package fm.example.demo.Controller;

import fm.example.demo.Entity.Forum;
import fm.example.demo.Entity.Message;
import fm.example.demo.Service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forums")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @PostMapping("/create")
    public Forum createForum(@RequestBody Forum forum) {
        return forumService.createForum(forum);
    }

    @GetMapping("/{id}")
    public Optional<Forum> getForumById(@PathVariable String id) {
        return forumService.getForumById(id);
    }
    @GetMapping("/{id}/messages")
    public Page<Message> getMessagesPaged(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return forumService.getMessagesPaged(id, PageRequest.of(page, size));
    }

    @GetMapping("/all")
    public List<Forum> getAllForums() {
        return forumService.getAllForums();
    }

    // New endpoint for pagination
    @GetMapping("/paged")
    public Page<Forum> getForumsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String forumName) {
        return forumService.getForumsPaged(page, size, forumName);
    }
}
