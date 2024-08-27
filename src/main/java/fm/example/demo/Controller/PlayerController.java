package fm.example.demo.Controller;

import fm.example.demo.Entity.Player;
import fm.example.demo.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/team/{teamId}")
    public List<Player> getAllPlayersByTeamID(@PathVariable Long teamId) {
        return playerService.getAllPlayersByTeamID(teamId);
    }
    @PostMapping("/add")
    public String addPlayer(@RequestBody Player player) {
        if (playerService.existsById(player.getId())) {
            return "Player with ID " + player.getId() + " already exists.";
        } else {
            playerService.addPlayer(player);
            return "Player added successfully.";
        }
    }
}
