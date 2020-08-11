package ngo.best.server.repository;

import ngo.best.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author Ioana
 */

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    List<User> findAllByLastName(String lastName);
}