package dat3.backend.service;

import dat3.backend.dto.ClubDTO;
import dat3.backend.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository){
        this.clubRepository = clubRepository;
    }

    public List<ClubDTO> getAllClubs(){
       return clubRepository.findAll().stream().map(club ->
        new ClubDTO(club)).toList();
    }


}
