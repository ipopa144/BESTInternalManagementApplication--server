package ngo.best.server.repository;

import ngo.best.server.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
