package dat3.backend.api;

import dat3.backend.dto.RefereeDTO;
import dat3.backend.dto.UsernameDTO;
import dat3.backend.service.UserService;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import org.springframework.security.access.prepost.PreAuthorize;
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



    @PostMapping("/referee")
    public UsernameDTO createReferee(@RequestBody RefereeDTO refereeDTO ){

        return  userService.addReferee(refereeDTO);
    }

    @GetMapping()
    public List<UserWithRolesRequest> getAllUsers(){
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PatchMapping("/refereePassword")
    public void editRefereePassword(Principal p, @RequestBody RefereeDTO refereeDTO) {
        userService.editRefereePassword(p.getName(), refereeDTO);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/makeAdmin/{username}")
    public void makeAdmin(@PathVariable String username){
        userService.makeAdmin(username);
    }
}
