package dat3.backend.service;

import dat3.backend.dto.TeamDTO;
import dat3.backend.entity.Team;
import dat3.backend.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamService
{
    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }

    public TeamDTO getTeamById(int id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        return new TeamDTO(team, false);
    }
}
