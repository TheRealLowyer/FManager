package fm.example.demo.Repo;

import fm.example.demo.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Method to find players by team ID
    List<Player> findByTeamId(Long teamId);

    @Query(value = "SELECT * FROM players WHERE position = :position AND team_id IS NULL LIMIT 2", nativeQuery = true)
    List<Player> findTop2ByPositionAndTeamIsNull(@Param("position") int position);

    @Query(value = "SELECT * FROM players WHERE position = :position AND team_id IS NULL LIMIT 6", nativeQuery = true)
    List<Player> findTop6ByPositionAndTeamIsNull(@Param("position") int position);

    @Query(value = "SELECT * FROM players WHERE position = :position AND team_id IS NULL LIMIT 4", nativeQuery = true)
    List<Player> findTop4ByPositionAndTeamIsNull(@Param("position") int position);
}
