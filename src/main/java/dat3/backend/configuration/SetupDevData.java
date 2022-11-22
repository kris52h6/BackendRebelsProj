package dat3.backend.configuration;

import dat3.backend.entity.Match;
import dat3.backend.repository.MatchRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class SetupDevData implements ApplicationRunner
{
    MatchRepository matchRepository;

    public SetupDevData(MatchRepository matchRepository)
    {
        this.matchRepository = matchRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        LocalDateTime ldt1 = LocalDateTime.of(2022, 10, 10, 18, 15);
        Match match1 = new Match("1", "abc", "abc", ldt1, "abc");
        matchRepository.save(match1);
    }
}
