package dat3.backend.repository;

import dat3.backend.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository<Division, String> {
}
