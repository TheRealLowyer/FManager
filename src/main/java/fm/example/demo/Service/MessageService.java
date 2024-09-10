package fm.example.demo.Service;

import fm.example.demo.Entity.Forum;
import fm.example.demo.Entity.Message;
import fm.example.demo.Repo.ForumRepository;
import fm.example.demo.Repo.MessageRepository;
import fm.example.demo.Repo.UserRepository; // Assuming you have a UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private UserRepository userRepository; // Assuming a UserRepository exists

    public Message addMessage(Message message) {
        // Check if the username exists
        if (!userRepository.findByUsername(message.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username does not exist");
        }

        // Check if the forum exists
        Optional<Forum> forum = forumRepository.findById(message.getForum().getId());
        if (!forum.isPresent()) {
            throw new IllegalArgumentException("Forum ID does not exist");
        }

        // Check if the message ID is unique
        if (messageRepository.existsById(message.getId())) {
            throw new IllegalArgumentException("Message ID must be unique");
        }

        // Save the message
        return messageRepository.save(message);
    }

    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesByForumId(String forumId) {
        return messageRepository.findByForumId(forumId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
