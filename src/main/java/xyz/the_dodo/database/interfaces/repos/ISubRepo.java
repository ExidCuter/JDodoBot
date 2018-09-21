package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.the_dodo.database.types.Subscription;

public interface ISubRepo extends JpaRepository<Subscription, Long> { }
