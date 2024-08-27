package fm.example.demo.Repo;

import fm.example.demo.Entity.Forum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends MongoRepository<Forum, String> {

    // Additional custom queries if needed

}
