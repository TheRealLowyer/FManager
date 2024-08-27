package fm.example.demo.Service;

import fm.example.demo.Entity.Player;
import fm.example.demo.Repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public List<Player> getAllPlayersByTeamID(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }
    public boolean existsById(Long id) {
        return playerRepository.existsById(id); // Use the repository method to check existence
    }
}
