package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.the_dodo.database.types.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
	User findByDiscordId(String discordId);
}
