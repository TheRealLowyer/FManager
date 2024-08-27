package fm.example.demo.Repo;

import fm.example.demo.Entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    // Additional custom queries if needed

}
