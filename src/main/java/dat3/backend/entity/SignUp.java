package dat3.backend.entity;

import dat3.backend.dto.MatchDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SignUp
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    @ManyToOne
    Match match;

    @ManyToOne
    Referee referee;
    
    String position;

    public SignUp(Match match, Referee referee, String position)
    {
        this.match = match;
        this.referee = referee;
        this.position = position;
    }
}
