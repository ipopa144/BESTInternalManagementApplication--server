package ngo.best.server.repository;

import ngo.best.server.model.entity.CoreTeamMember;
import ngo.best.server.model.entity.CoreTeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ioana
 */

public interface CoreTeamMemberRepository extends JpaRepository<CoreTeamMember, CoreTeamMemberId> {
}
