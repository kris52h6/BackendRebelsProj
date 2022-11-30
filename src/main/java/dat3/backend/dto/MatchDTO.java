package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Match;
import dat3.backend.entity.Team;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MatchDTO
{
    Integer id;
    List<Integer> signUpIds = new ArrayList<>();
    List<String> acceptedReferees = new ArrayList<>();
    int homeTeamId;
    int awayTeamId;
    int refereeTeamId;
    LocalDateTime startTime;
    String divisionName;

    public static Match getMatchEntity(MatchDTO matchDTO, Team homeTeam, Team awayTeam, Team refereeTeam) {
        return new Match(
                homeTeam,
                awayTeam,
                refereeTeam,
                matchDTO.getStartTime(),
                homeTeam.getDivision()
        );
    }

    public MatchDTO(Match match,boolean includeAll)
    {
        this.signUpIds = match.getSignUps().stream().map(signUp -> signUp.getId()).collect(Collectors.toList());
        this.acceptedReferees = match.getAcceptedReferees().stream().map(accepted -> accepted.getUsername()).toList();
        this.homeTeamId = match.getHomeTeam().getId();
        this.awayTeamId = match.getAwayTeam().getId();
        this.refereeTeamId = match.getRefereeTeam().getId();
        this.startTime = match.getStartTime();
        this.divisionName = match.getDivision().getName();
        if (includeAll){
            this.id = match.getId();
        }
    }
}
