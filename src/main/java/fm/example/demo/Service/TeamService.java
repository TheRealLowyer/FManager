package fm.example.demo.Service;

import fm.example.demo.Entity.Team;
import fm.example.demo.Repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    // Method to create or save a team
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    // Method to find a team by its ID
    public Optional<Team> findTeamById(Long id) {
        return teamRepository.findById(id);
    }

    // Method to find a team by its name
    public Optional<Team> findTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    // Method to find all teams
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    // Method to delete a team by its ID
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
