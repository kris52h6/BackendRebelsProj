package dat3.backend.api;

import dat3.backend.dto.MatchDTO;
import dat3.backend.service.MatchService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/match")
@RestController
@CrossOrigin
public class MatchController
{
    MatchService matchService;

    public MatchController(MatchService matchService)
    {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    MatchDTO getMatch(@PathVariable int matchId){
        MatchDTO test = matchService.getMatchById(matchId);
        System.out.println(test);
        return test;
    }
}
