package fm.example.demo.Repo;

import fm.example.demo.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    // Additional custom queries if needed
    Optional<Team> findByName(String name);
}
