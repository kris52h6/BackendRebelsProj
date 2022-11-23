package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Match;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MatchDTO
{
    Integer id;
    List<Integer> signUpIds = new ArrayList<>();
    int homeTeamId;
    int awayTeamId;
    LocalDateTime startTime;
    String division;

    public MatchDTO(Match match,boolean includeAll)
    {
        this.signUpIds = match.getSignUp().stream().map(signUp -> signUp.getId()).toList();
        this.homeTeamId = match.getHomeTeam().getId();
        this.awayTeamId = match.getAwayTeam().getId();
        this.startTime = match.getStartTime();
        this.division = match.getDivision();
        if (includeAll){
            this.id = match.getId();
        }
    }
}
