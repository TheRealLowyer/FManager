package fm.example.demo.Controller;

import fm.example.demo.Entity.Message;
import fm.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public Message addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable String id) {
        return messageService.getMessageById(id);
    }

    @GetMapping("/forum/{forumId}")
    public List<Message> getMessagesByForumId(@PathVariable String forumId) {
        return messageService.getMessagesByForumId(forumId);
    }

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
