package dat3.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.backend.entity.Match;
import dat3.backend.entity.Referee;
import dat3.backend.entity.SignUp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpDTO
{
    Integer id;
    int matchId;
    String refereeUsername;
    String position;

    public SignUpDTO(SignUp s, boolean includeAll)
    {
        this.matchId = s.getMatch().getId();
        this.refereeUsername = s.getReferee().getUsername();
        this.position = s.getPosition();
        if (includeAll){
            this.id = s.getId();
        }
    }
}
