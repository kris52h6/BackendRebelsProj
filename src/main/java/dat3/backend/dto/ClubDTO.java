package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Club;
import dat3.backend.entity.Referee;
import dat3.backend.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClubDTO {

    String name;
    String address;
    String email;
    List<Team> teams;
    List<Referee> referees;

    public ClubDTO(Club c) {
        this.name = c.getName();
        this.address = c.getAddress();
        this.email = c.getEmail();
        this.teams = c.getTeams();
        this.referees = c.getReferees();
    }

}
