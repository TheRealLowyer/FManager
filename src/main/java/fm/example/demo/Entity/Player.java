package fm.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    @JsonIgnoreProperties({"players"})
    private Team team;

    public enum Position {
        GK, DEF, MID, FOR
    }
    private int age;
    @Column(columnDefinition = "LONGTEXT")
    private String base64Photo;
    // New fields with validation
    private int experience;
    private int playerCondition;
    private int health;
    private int moral;
    private int form;
    private int reflex;
    private int handControl;
    private int turnOver;
    private int headControl;
    private int speed;
    private int ortaAçma;
    private int pass;
    private int shoot;
    private int technic;
    private int power;

}