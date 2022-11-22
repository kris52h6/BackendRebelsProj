package dat3.backend.repository;

import dat3.backend.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Integer>
{

}
