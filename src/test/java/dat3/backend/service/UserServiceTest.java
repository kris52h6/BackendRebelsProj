package dat3.backend.service;

import dat3.security.dto.UserWithRolesRequest;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceTest {

 /*   public UserService userService;
    public static UserWithRolesRepository userWithRolesRepository;

    @BeforeAll
    public static void setUpData(@Autowired UserWithRolesRepository userWithRoles_Repository) {
        userWithRolesRepository = userWithRoles_Repository;
        userWithRolesRepository.deleteAll();
        List<UserWithRoles> users = List.of(
                new UserWithRoles("user1", "test12", "user1@a.dk"),
                new UserWithRoles("user2", "test12", "user2@a.dk")
        );
        userWithRolesRepository.saveAll(users);
    }

    @BeforeEach
    public void setUserService(){
        userService = new UserService(userWithRolesRepository);
    }


/*    @Test
    void addUser() {
        UserWithRolesRequest userRequest = new UserWithRolesRequest("user3","test12","user3@a.dk",null,null,null,false);
        userService.addUser(userRequest);
        assertEquals(3, userWithRolesRepository.findAll().size());
    }*/
}