package dat3.backend.service;

import dat3.backend.dto.MatchDTO;
import dat3.backend.entity.Match;
import dat3.backend.repository.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatchService
{
    MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository)
    {
        this.matchRepository = matchRepository;
    }

    public MatchDTO getMatchById(int id){
        Match foundMatch = matchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        return new MatchDTO(foundMatch, false);
    }
}
