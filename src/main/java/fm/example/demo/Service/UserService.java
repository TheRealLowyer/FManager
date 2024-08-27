package fm.example.demo.Service;
import fm.example.demo.Entity.Team;
import fm.example.demo.Repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import fm.example.demo.Entity.User;
import fm.example.demo.Entity.Player;
import fm.example.demo.Repo.UserRepository;
import fm.example.demo.Repo.PlayerRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Method to create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User signUpUser(User user) {
        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Check if a team with the given name already exists
        Optional<Team> existingTeam = teamRepository.findByName(user.getTeam().getName());
        Team team;

        if (existingTeam.isPresent()) {
            team = existingTeam.get();
        } else {
            team = new Team();
            team.setName(user.getTeam().getName());
            // Save the team first before associating it with the user
            team = teamRepository.save(team);
        }

        // Assign the team to the user
        user.setTeam(team);

        // Fetch and assign specific players to the new team
        List<Player> goalkeepers = playerRepository.findTop2ByPositionAndTeamIsNull(0); // 0 for GK
        List<Player> defenders = playerRepository.findTop6ByPositionAndTeamIsNull(1);  // 1 for DEF
        List<Player> midfielders = playerRepository.findTop6ByPositionAndTeamIsNull(2); // 2 for MID
        List<Player> forwards = playerRepository.findTop4ByPositionAndTeamIsNull(3);    // 3 for FOR

        // Combine all players into a single list
        List<Player> playersToAssign = new ArrayList<>();
        playersToAssign.addAll(goalkeepers);
        playersToAssign.addAll(defenders);
        playersToAssign.addAll(midfielders);
        playersToAssign.addAll(forwards);

        // Assign the new team to each player
        for (Player player : playersToAssign) {
            player.setTeam(team);
        }

        // Save the players with the new team assignment
        playerRepository.saveAll(playersToAssign);

        // Save and return the user
        return userRepository.save(user);
    }






    // Method to find a user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to find a user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public String login(String username, String password) {
        Optional<User> userOptional = findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Generate and return JWT token (if applicable)
                return "Login successful!";
            }
        }
        throw new RuntimeException("Invalid username or password!");
    }

    // Other methods like updateUser, deleteUser, etc.
}
