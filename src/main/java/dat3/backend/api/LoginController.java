package dat3.backend.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    @GetMapping
    public String getInfoNotLoggedIn() {
        return "Hello i am not logged in";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getUserAdminInfo() {
        return "You are logged in as admin";
    }

    @PreAuthorize("hasAuthority('DOMMER')")
    @GetMapping("/dommer")
    public String getDommerInfo() {
        return "You are logged in as DOMMER";
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/user")
    public String getUserInfo() {
        return "You are logged in as USER";
    }


    @GetMapping("/user-fromtoken")
    public String getUserInfo(Principal p) {
        String info = "Current user is "+p.getName();
        return info;
    }

}
