package fm.example.demo.Service;

import fm.example.demo.Entity.Player;
import fm.example.demo.Repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersByTeamId(String teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(String id) {
        playerRepository.deleteById(id);
    }
    public List<Player> getUnassignedPlayers() {
        return playerRepository.findByTeamId(null);
    }
}
