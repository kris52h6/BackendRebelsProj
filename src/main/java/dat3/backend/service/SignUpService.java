package dat3.backend.service;

import dat3.backend.dto.SignUpDTO;
import dat3.backend.entity.SignUp;
import dat3.backend.repository.SignUpRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SignUpService
{
    SignUpRepository signUpRepository;

    public SignUpService(SignUpRepository signUpRepository)
    {
        this.signUpRepository = signUpRepository;
    }

    public SignUpDTO getSignUpById(int id){
        SignUp foundId = signUpRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        return new SignUpDTO(foundId, false);
    }

    public List<SignUpDTO> getAllSignUpsByMatchId(int matchId){
        List<SignUp> foundSignUps = signUpRepository.findAllByMatch_Id(matchId);
        return foundSignUps.stream().map(s -> new SignUpDTO(s,true)).toList();
    }
}
