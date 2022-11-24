package dat3.backend.api;

import dat3.backend.dto.RefereeDTO;
import dat3.backend.service.UserService;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public void createUser(@RequestBody UserWithRolesRequest userWithRolesRequest){
        userService.addUser(userWithRolesRequest);
    }

    @PostMapping("/referee")
    public void createReferee(@RequestBody RefereeDTO refereeDTO ){
        userService.addReferee(refereeDTO);
    }

    @GetMapping()
    public List<UserWithRolesRequest> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/referees")
    public List<RefereeDTO> getAllReferees(){
        return userService.getAllReferees();
    }

    @PatchMapping("/referee")
    public void editReferee(Principal p, @RequestBody RefereeDTO refereeDTO){
        userService.editReferee(p.getName(), refereeDTO);
    }

    @GetMapping("/referee")
    public RefereeDTO getReferee(Principal p){
        return userService.getReferee(p.getName());
    }
}
