package dat3.backend.api;

import dat3.backend.service.UserService;
import dat3.security.dto.LoginRequest;
import dat3.security.dto.UserWithRolesRequest;
import org.springframework.web.bind.annotation.*;

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
}
