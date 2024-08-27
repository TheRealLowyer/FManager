package fm.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "players")
public class Player {

    @Id
    private Long id;
    private String name;
    private String surname;
    private Position position;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    public enum Position {
        GK, DEF, MID, FOR
    }



}