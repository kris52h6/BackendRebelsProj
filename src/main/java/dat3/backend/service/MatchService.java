package dat3.backend.service;

import dat3.backend.dto.MatchDTO;
import dat3.backend.entity.Match;
import dat3.backend.entity.Team;
import dat3.backend.repository.MatchRepository;
import dat3.backend.repository.SignUpRepository;
import dat3.backend.repository.TeamRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatchService
{
    MatchRepository matchRepository;
    TeamRepository teamRepository;
    SignUpRepository signUpRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, SignUpRepository signUpRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.signUpRepository = signUpRepository;
    }

    public MatchDTO getMatchById(int id){
        Match foundMatch = matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        return new MatchDTO(foundMatch, false);
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll()
                .stream()
                .map(m -> new MatchDTO(m, true))
                .toList();
    }

    public List<MatchDTO> getAllAcceptedSignUps(){
        return matchRepository.findAll()
                .stream()
                .map(a -> new MatchDTO(a, true))
                .toList();
    }

    public boolean deleteMatchById(int matchId) {
        try {
            Match match = matchRepository.findById(matchId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
            System.out.println(match);
            matchRepository.delete(match);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addMatch(MatchDTO matchDTO) {
        try {
            Team homeTeam = teamRepository.findById(matchDTO.getHomeTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Home team not found"));
            Team awayTeam = teamRepository.findById(matchDTO.getAwayTeamId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Away team not found"));

            Match createNewMatch = matchDTO.getMatchEntity(matchDTO, homeTeam, awayTeam);
            matchRepository.save(createNewMatch);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAccepted(MatchDTO matchDTO){
        try
        {
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
