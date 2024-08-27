package fm.example.demo.Repo;

import fm.example.demo.Entity.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, String> {
    Page<Forum> findByForumNameContainingIgnoreCase(String forumName, Pageable pageable);

}
