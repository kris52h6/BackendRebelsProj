package dat3.backend.api;

import dat3.backend.dto.MatchDTO;
import dat3.backend.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    MatchDTO getMatchById(@PathVariable int matchId)
    {
        return matchService.getMatchById(matchId);
    }

    @GetMapping()
    List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/accepted")
    List<MatchDTO> getAllAcceptedSignUps(){
        System.out.println(matchService.getAllAcceptedSignUps().toString());
        return matchService.getAllAcceptedSignUps();
    }

    @PatchMapping()
    boolean addAccepted(@RequestBody MatchDTO matchDTO){
        return matchService.addAccepted(matchDTO);
    }

    @PostMapping()
    boolean addMatch(@RequestBody MatchDTO matchDTO) {
        return matchService.addMatch(matchDTO);
    }

    @DeleteMapping("/{matchId}")
    boolean deleteMatchById(@PathVariable int matchId) {
        return matchService.deleteMatchById(matchId);
    }
}

