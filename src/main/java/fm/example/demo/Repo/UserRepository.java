package fm.example.demo.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import fm.example.demo.Entity.User;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByTeamId(String teamId);
}

