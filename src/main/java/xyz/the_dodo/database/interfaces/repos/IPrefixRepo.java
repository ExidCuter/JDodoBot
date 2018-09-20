package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.the_dodo.database.types.Prefix;

public interface IPrefixRepo extends JpaRepository<Prefix, Long> {
}
