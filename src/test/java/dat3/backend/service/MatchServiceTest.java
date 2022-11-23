package dat3.backend.service;

import dat3.backend.dto.MatchDTO;
import dat3.backend.entity.Match;
import dat3.backend.entity.Team;
import dat3.backend.repository.MatchRepository;
import dat3.backend.repository.SignUpRepository;
import dat3.backend.repository.TeamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MatchServiceTest {

    MatchService matchService;
    public static MatchRepository matchRepository;
    public static TeamRepository teamRepository;
    public static SignUpRepository signUpRepository;

    @BeforeAll
    public static void setUpData(@Autowired MatchRepository match_Repository,
                     @Autowired TeamRepository team_Repository,
                     @Autowired SignUpRepository signUp_Repository) {
        matchRepository = match_Repository;
        teamRepository = team_Repository;
        signUpRepository = signUp_Repository;
        matchRepository.deleteAll();

        Team team1 = new Team("Herlev Rebels", "U13");
        Team team2 = new Team("Copenhagen Towers", "U13");
        teamRepository.save(team1);
        teamRepository.save(team2);

        LocalDateTime ldt1 = LocalDateTime.of(2022, 10, 10, 18, 15);

        List<Match> matches = List.of(
                new Match(team1, team2, ldt1, "U13"),
                new Match(team2, team1, ldt1, "U13")
        );
        match_Repository.saveAll(matches);
    }

    @BeforeEach
    public void setMatchService() {
        matchService = new MatchService(matchRepository, teamRepository, signUpRepository);
    }


    @Test
    void getMatchById() {
        MatchDTO match = matchService.getMatchById(1);
        assertEquals("U13", match.getDivision());
    }

    @Test
    void getAllMatches() {
        List<MatchDTO> matches = matchService.getAllMatches();

        assertEquals(2, matches.size());
    }

    @Test
    void deleteMatchById() {
        Match matchToBeDeleted = matchRepository.findById(1).orElseThrow();
        matchRepository.delete(matchToBeDeleted);

        assertEquals(1, matchService.getAllMatches().size());
    }

    @Test
    void addMatch() {
        Team t1 = teamRepository.findById(1).orElseThrow();
        Team t2 = teamRepository.findById(2).orElseThrow();
        String division = "U15";
        LocalDateTime ldt1 = LocalDateTime.of(2022, 10, 10, 18, 15);

        Match createNewMatch = new Match(t1, t2, ldt1, division);
        MatchDTO matchDTO = new MatchDTO(createNewMatch, true);
        matchService.addMatch(matchDTO);

        List<MatchDTO> matches = matchService.getAllMatches();

        assertThat(matches, containsInAnyOrder(
                hasProperty("division", is("U15")),
                hasProperty("division", is("U13")),
                hasProperty("division", is("U13"))
        ));
    }
}