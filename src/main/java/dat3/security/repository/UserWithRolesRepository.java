package dat3.security.repository;

import dat3.backend.entity.Referee;
import dat3.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles, String> {
    Boolean existsByEmail(String email);
}
