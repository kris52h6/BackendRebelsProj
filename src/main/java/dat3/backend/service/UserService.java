package dat3.backend.service;


import dat3.backend.entity.Referee;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserWithRolesRepository userWithRolesRepository;

    public UserService(UserWithRolesRepository userWithRolesRepository){
        this.userWithRolesRepository = userWithRolesRepository;
    }

    public void addUser(UserWithRolesRequest userWithRolesRequest){

        UserWithRoles newUser = new UserWithRoles(userWithRolesRequest.getUsername(), userWithRolesRequest.getPassword(), userWithRolesRequest.getEmail());
        newUser.addRole(Role.USER);
        userWithRolesRepository.save(newUser);

    }

    public void addReferee(UserWithRolesRequest userWithRolesRequest) {
        Referee newUser = new Referee(userWithRolesRequest.getUsername(), userWithRolesRequest.getPassword(), userWithRolesRequest.getEmail(),
                userWithRolesRequest.getPosition(), userWithRolesRequest.getLicense(), userWithRolesRequest.getBankInformation());
        newUser.addRole(Role.REFEREE);
        newUser.addRole(Role.USER);
        userWithRolesRepository.save(newUser);

    }
}
