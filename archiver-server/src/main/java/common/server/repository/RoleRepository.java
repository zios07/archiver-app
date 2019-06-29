package common.server.repository;

import common.server.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleCode(String roleCode);
}
