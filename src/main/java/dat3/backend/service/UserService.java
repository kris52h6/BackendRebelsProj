package dat3.backend.service;


import dat3.backend.dto.RefereeDTO;
import dat3.backend.entity.Referee;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.RefereeRepository;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserWithRolesRepository userWithRolesRepository;
    RefereeRepository refereeRepository;

    public UserService(UserWithRolesRepository userWithRolesRepository, RefereeRepository refereeRepository){
        this.userWithRolesRepository = userWithRolesRepository;
        this.refereeRepository = refereeRepository;
    }

    public void addUser(UserWithRolesRequest userWithRolesRequest){

        UserWithRoles newUser = new UserWithRoles(userWithRolesRequest.getUsername(), userWithRolesRequest.getPassword(), userWithRolesRequest.getEmail(), userWithRolesRequest.getFirstname(), userWithRolesRequest.getLastname());
        newUser.addRole(Role.USER);
        userWithRolesRepository.save(newUser);

    }

    public void addReferee(RefereeDTO refereeDTO) {
        Referee newUser = new Referee(refereeDTO.getUsername(),
                refereeDTO.getPassword(),
                refereeDTO.getEmail(),
                refereeDTO.getFirstname(),
                refereeDTO.getLastname(),
                refereeDTO.getPosition(),
                refereeDTO.getBankInformation());
        newUser.setLicense(refereeDTO.getLicense());




        newUser.addRole(Role.REFEREE);
        newUser.addRole(Role.USER);
        userWithRolesRepository.save(newUser);

    }

    public List<UserWithRolesRequest> getAllUsers(){
        return userWithRolesRepository.findAll().stream().map(user -> new UserWithRolesRequest(user)).toList();
    }


    // EVENTUELT CAST TIL REFEREE OBJECT
    public List<RefereeDTO> getAllReferees(){
        return refereeRepository.findAll().stream().map(ref -> new RefereeDTO(ref)).toList();
    }


}
