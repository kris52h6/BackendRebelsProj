package dat3.backend.api;

import dat3.backend.dto.SignUpDTO;
import dat3.backend.service.SignUpService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/signups")
public class SignUpController
{
    SignUpService signUpService;

    public SignUpController(SignUpService signUpService)
    {
        this.signUpService = signUpService;
    }

    @GetMapping("/{signupId}")
    public SignUpDTO getSignUpById(@PathVariable int signupId){
        return signUpService.getSignUpById(signupId);
    }
}
