package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.the_dodo.database.types.Server;

@Repository
public interface IServerRepo extends JpaRepository<Server, Long> {
	Server findByDiscordId(String discordId);
}
