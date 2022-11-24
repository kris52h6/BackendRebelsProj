package dat3.backend.service;


import dat3.backend.dto.RefereeDTO;
import dat3.backend.dto.UsernameDTO;
import dat3.backend.entity.Referee;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.RefereeRepository;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public void editReferee(String username, RefereeDTO refereeDTO) {
        Referee refereeToEdit = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));

        refereeToEdit.setFirstname(refereeDTO.getFirstname());
        refereeToEdit.setLastname(refereeDTO.getLastname());
        refereeToEdit.setEmail(refereeDTO.getEmail());
        refereeToEdit.setBankInformation(refereeDTO.getBankInformation());
        refereeToEdit.setLicense(refereeDTO.getLicense());

        refereeRepository.save(refereeToEdit);
    }

    public RefereeDTO getReferee(String username) {
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));

        RefereeDTO refereeDTO = new RefereeDTO(referee);
        return refereeDTO;
    }

    public UsernameDTO getUserNameDTO(String username) {
        UsernameDTO usernameDTO = new UsernameDTO(username);
        return usernameDTO;
    }

    public void editRefereePassword(String username, RefereeDTO refereeDTO) {
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));
        referee.setPassword(refereeDTO.getPassword());
        refereeRepository.save(referee);
    }

    public void makeAdmin(String username) {
        Referee referee = refereeRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Referee not found"));
        referee.addRole(Role.ADMIN);
        refereeRepository.save(referee);
    }
}
