package fm.example.demo.Repo;

import fm.example.demo.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    // Additional custom queries if needed

}
