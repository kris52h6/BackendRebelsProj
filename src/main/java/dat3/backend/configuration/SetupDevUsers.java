package dat3.backend.configuration;

import dat3.backend.entity.*;
import dat3.backend.repository.*;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.time.LocalDateTime;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    TeamRepository teamRepository;
    SignUpRepository signUpRepository;
    MatchRepository matchRepository;
    ClubRepository clubRepository;

    DivisionRepository divisionRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, TeamRepository teamRepository, SignUpRepository signUpRepository, MatchRepository matchRepository, ClubRepository clubRepository, DivisionRepository divisionRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.teamRepository = teamRepository;
        this.signUpRepository = signUpRepository;
        this.matchRepository = matchRepository;
        this.clubRepository = clubRepository;
        this.divisionRepository = divisionRepository;

        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1",  passwordUsedByAll, "user1@a.dk", "firstname1", "lastname1");
        UserWithRoles user2 = new UserWithRoles("user2",  passwordUsedByAll, "user2@a.dk", "firstname2", "lastname2");
        UserWithRoles user3 = new UserWithRoles("user3",  passwordUsedByAll, "user3@a.dk", "firstname3", "lastname3");
        UserWithRoles user4 = new UserWithRoles("user4",  passwordUsedByAll, "user4@a.dk", "firstname4", "lastname4");
        UserWithRoles user5 = new UserWithRoles("user5",  passwordUsedByAll, "user5@a.dk", "firstname5", "lastname5");



        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        user5.addRole(Role.REFEREE);

        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
        userWithRolesRepository.save(user5);

        Referee referee1 = new Referee("referee1", passwordUsedByAll, "ref1@a.dk","firstname10", "lastname10");
        referee1.addRole(Role.REFEREE);
        userWithRolesRepository.save(referee1);

        Team team1 = new Team("Herlev Rebels", "U13");
        Team team2 = new Team("Copenhagen Towers", "U13");
        Team team12 = new Team("Søllerød Golddiggers", "U13");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team12);

        LocalDateTime ldt1 = LocalDateTime.of(2022, 10, 10, 18, 15);
        Match match1 = new Match(team1, team12, ldt1, "U13");
        Match match2 = new Match(team1, team2, ldt1, "U13");
        matchRepository.save(match1);
        matchRepository.save(match2);


        SignUp signUp1 = new SignUp(match1, referee1, "ref");
        SignUp signUp2 = new SignUp(match2, referee1, "ref");
        signUpRepository.save(signUp1);
        signUpRepository.save(signUp2);


        Club club1 = new Club("Club1", "Club1Sted", "club@club1.com");
        Club club2 = new Club("Club2", "Club2Sted", "club@club2.com");

        clubRepository.save(club1);
        clubRepository.save(club2);

        Division division1 = new Division("U13",90,100, "LOL");
        Division division2 = new Division("U15",90,100, "LOL");
        Division division3 = new Division("U17",90,100, "LOL");
        Division division4 = new Division("U19",90,100, "LOL");

        divisionRepository.save(division1);
        divisionRepository.save(division2);
        divisionRepository.save(division3);
        divisionRepository.save(division4);

    }
}
