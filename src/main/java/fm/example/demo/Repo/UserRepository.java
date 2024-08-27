package fm.example.demo.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import fm.example.demo.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}

