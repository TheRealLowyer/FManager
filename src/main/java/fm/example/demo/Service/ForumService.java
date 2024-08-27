package fm.example.demo.Service;

import fm.example.demo.Entity.Forum;
import fm.example.demo.Entity.Message;
import fm.example.demo.Repo.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    public Forum createForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public Optional<Forum> getForumById(String id) {
        return forumRepository.findById(id);
    }

    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }
    public Page<Message> getMessagesPaged(String forumId, Pageable pageable) {
        Optional<Forum> forum = forumRepository.findById(forumId);
        if (forum.isPresent()) {
            List<Message> messages = forum.get().getMessages();
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), messages.size());
            List<Message> subList = messages.subList(start, end);
            return new PageImpl<>(subList, pageable, messages.size());
        }
        return Page.empty();
    }

    // New method for paginated forums
    public Page<Forum> getForumsPaged(int page, int size, String forumName) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("forumType").ignoreCase(), Sort.Order.asc("forumName")));

        if (forumName != null && !forumName.isEmpty()) {
            // Search by forum name and prioritize GENERAL forums first
            return forumRepository.findByForumNameContainingIgnoreCase(forumName, pageRequest);
        } else {
            // Retrieve all forums, prioritizing GENERAL forums first
            return forumRepository.findAll(pageRequest);
        }
    }

}
