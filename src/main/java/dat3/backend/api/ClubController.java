package dat3.backend.api;

import dat3.backend.dto.ClubDTO;
import dat3.backend.service.ClubService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/clubs")
public class ClubController {

    ClubService clubService;

    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubDTO> getAllClubs(){
        return clubService.getAllClubs();
    }


}
