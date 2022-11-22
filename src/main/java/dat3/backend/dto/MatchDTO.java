package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Match;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@JsonInclude
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
