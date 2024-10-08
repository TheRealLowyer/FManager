package fm.example.demo.Repo;

import fm.example.demo.Entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, String> {

    // Additional custom queries if needed

}
