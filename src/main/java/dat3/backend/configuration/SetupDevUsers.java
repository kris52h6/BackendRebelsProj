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
import java.util.ArrayList;
import java.util.List;

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
        Referee referee2 = new Referee("referee2", passwordUsedByAll, "ref2@a.dk","firstname2", "lastname10");
        referee1.addRole(Role.REFEREE);
        referee2.addRole(Role.REFEREE);
        referee1.setLicense("A");
        referee2.setLicense("A");
        userWithRolesRepository.save(referee1);
        userWithRolesRepository.save(referee2);

        Division division1 = new Division("U13",90,100, "LOL");
        Division division2 = new Division("U15",90,100, "LOL");
        Division division3 = new Division("U17",90,100, "LOL");
        Division division4 = new Division("U19",90,100, "LOL");

        divisionRepository.save(division1);
        divisionRepository.save(division2);
        divisionRepository.save(division3);
        divisionRepository.save(division4);

        Team team1 = new Team("Herlev Rebels", division1);
        Team team2 = new Team("Copenhagen Towers", division1);
        Team team3 = new Team("Søllerød Golddiggers", division1);
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);

        LocalDateTime ldt1 = LocalDateTime.of(2022, 10, 10, 18, 15);
        Match match1 = new Match(team1, team3, team2, ldt1, division1);
        Match match2 = new Match(team1, team2, team3, ldt1, division1);
        Match match3 = new Match(team2, team1, team3, ldt1, division1);
        matchRepository.save(match1);
        matchRepository.save(match2);
        matchRepository.save(match3);


        SignUp signUp1 = new SignUp(match1, referee1, "ref");
        SignUp signUp2 = new SignUp(match2, referee1, "ref");
        SignUp signUp3 = new SignUp(match1, referee2, "ref");
        signUpRepository.save(signUp1);
        signUpRepository.save(signUp2);
        signUpRepository.save(signUp3);

        List<Referee> acceptedReferees = new ArrayList<>();
        acceptedReferees.add(referee1);
        acceptedReferees.add(referee2);


        Match match4 = new Match(team2, team1, team3, ldt1, division1);
        matchRepository.save(match4);
        match4.setAcceptedReferees(acceptedReferees);
        match1.setAcceptedReferees(acceptedReferees);
        matchRepository.save(match4);
        matchRepository.save(match1);

        Club club1 = new Club("Club1", "Club1Sted", "club@club1.com");
        Club club2 = new Club("Club2", "Club2Sted", "club@club2.com");

        clubRepository.save(club1);
        clubRepository.save(club2);



    }
}
