package dat3.backend.repository;

import dat3.backend.entity.Match;
import dat3.backend.entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Integer>
{

}
