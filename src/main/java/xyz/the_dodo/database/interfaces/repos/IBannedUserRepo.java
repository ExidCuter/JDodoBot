package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.the_dodo.database.types.BannedUser;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.User;

import java.util.List;

@Repository
public interface IBannedUserRepo extends JpaRepository<BannedUser, Long> {
}
