package xyz.the_dodo.database.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

@Repository
public interface IAdminoRepo extends JpaRepository<Admin, Long>
{

}
