package dat3.backend.service;

import dat3.backend.entity.Club;
import dat3.backend.entity.Match;
import dat3.backend.repository.ClubRepository;
import dat3.security.repository.RefereeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClubServiceTest {

    ClubService clubService;
    public static ClubRepository clubRepository;
    public static RefereeRepository refereeRepository;

    @BeforeAll
    public static void setupData(@Autowired ClubRepository club_Repository, @Autowired RefereeRepository referee_Repository) {
        clubRepository = club_Repository;
        refereeRepository = referee_Repository;
        Club club1 = new Club("Herlev Rebels", "Dilhaven 40, 2730 Herlev", "Herlev@Rebels.com", "rebels");
        clubRepository.save(club1);
    }

    @BeforeEach
    public void setupService() {
        clubService = new ClubService(clubRepository, refereeRepository);
    }

    @Test
    void getAllClubs() {
        assertEquals(2, clubService.getAllClubs().size());
    }
}