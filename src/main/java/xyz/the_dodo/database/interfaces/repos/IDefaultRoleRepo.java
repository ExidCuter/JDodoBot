package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.the_dodo.database.types.DefaultRole;

@Repository
public interface IDefaultRoleRepo extends JpaRepository<DefaultRole, Long> {
	DefaultRole getDefaultRoleByServerId(Long serverId);
}
