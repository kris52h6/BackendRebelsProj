package dat3.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToMany
    List<Match> matches;
    String name;
    String division;

    public Team(String name, String division)
    {
        this.name = name;
        this.division = division;
    }
}
