package dat3.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "kamp")
public class Match
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String signUp;
    String homeTeam;
    String awayTeam;
    LocalDateTime startTime;
    String division;

    public Match(String signUp, String homeTeam, String awayTeam, LocalDateTime startTime, String division)
    {
        this.signUp = signUp;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = startTime;
        this.division = division;
    }



}
