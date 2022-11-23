package dat3.backend.api;

import dat3.backend.dto.MatchDTO;
import dat3.backend.service.MatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/matches")
public class MatchController
{
    MatchService matchService;

    public MatchController(MatchService matchService)
    {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    MatchDTO getMatchById(@PathVariable int matchId) throws Exception
    {
        return matchService.getMatchById(matchId);
    }
}

