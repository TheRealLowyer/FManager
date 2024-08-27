package fm.example.demo.Service;

import fm.example.demo.Entity.Forum;
import fm.example.demo.Entity.Message;
import fm.example.demo.Repo.ForumRepository;
import fm.example.demo.Repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    @Autowired
    private ForumRepository ForumRepository;

    @Autowired
    private MessageRepository MessageRepository;

    public List<Forum> getAllForums() {
        return ForumRepository.findAll();
    }


    public List<Message> getMessagesFromForum(String forumId) {
        Forum forum = ForumRepository.findById(forumId).orElse(null);
        if (forum != null) {
            return forum.getMessages();
        }
        return null;
    }

    public Forum addMessageToForum(String forumId, Message message) {
        Optional<Forum> forumOptional = ForumRepository.findById(forumId);
        if (forumOptional.isPresent()) {
            Forum forum = forumOptional.get();

            // Set the forum reference in the message
            message.setForum(forum);

            // Save the message to the database
            MessageRepository.save(message);

            // Add the message to the forum's message list
            forum.getMessages().add(message);

            return ForumRepository.save(forum);
        }
        return null;
    }

    // Other methods...
}
