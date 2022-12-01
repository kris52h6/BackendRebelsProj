package dat3.backend.repository;

import dat3.backend.entity.Club;
import dat3.backend.entity.Referee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.sql.Ref;
import java.util.List;

public interface ClubRepository extends JpaRepository<Club, String> {
}
