package dat3.backend.service;

import dat3.backend.dto.MatchDTO;
import dat3.backend.dto.PatchRefereeDTO;
import dat3.backend.entity.*;
import dat3.backend.repository.DivisionRepository;
import dat3.backend.repository.MatchRepository;
import dat3.backend.repository.SignUpRepository;
import dat3.backend.repository.TeamRepository;
import dat3.security.repository.RefereeRepository;
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
    RefereeRepository refereeRepository;
    DivisionRepository divisionRepository;

    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository, SignUpRepository signUpRepository, RefereeRepository refereeRepository, DivisionRepository divisionRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.signUpRepository = signUpRepository;
        this.refereeRepository = refereeRepository;
        this.divisionRepository = divisionRepository;
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

    public List<MatchDTO> getAllAcceptedReferees(){
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
            Team refereeTeam = teamRepository.findById(matchDTO.getRefereeTeamId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Referee team not found"));

            Match createNewMatch = matchDTO.getMatchEntity(matchDTO, homeTeam, awayTeam, refereeTeam);
            matchRepository.save(createNewMatch);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAccepted(PatchRefereeDTO patchRefereeDTO){
        try
        {
            Match foundMatch = matchRepository.findById(patchRefereeDTO.getMatchId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "match not found"));;
            Referee foundReferee = refereeRepository.findById(patchRefereeDTO.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Referee not found"));
            SignUp foundSignup = signUpRepository.findById(patchRefereeDTO.getSignupId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Signup not found"));
            foundMatch.addReferee(foundReferee);
            signUpRepository.delete(foundSignup);
            matchRepository.save(foundMatch);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<MatchDTO> getAllMatchesByDivisionId(String divisionId) {
        Division divisionFound = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Division not found"));
        return matchRepository.findAllByDivision(divisionFound).stream().map(m -> new MatchDTO(m, true)).toList();
    }
}
