package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Match;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MatchDTO
{
    Integer id;
    String signUp;
    String homeTeam;
    String awayTeam;
    LocalDateTime startTime;
    String division;

    public MatchDTO(Match match,boolean includeAll)
    {
        this.signUp = match.getSignUp();
        this.homeTeam = match.getHomeTeam();
        this.awayTeam = match.getAwayTeam();
        this.startTime = match.getStartTime();
        this.division = match.getDivision();
        if (includeAll){
            this.id = match.getId();
        }
    }
}
