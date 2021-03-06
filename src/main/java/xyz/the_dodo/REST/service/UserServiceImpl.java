package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IUserService;
import xyz.the_dodo.database.types.User;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepo userRepo;

    public void setUserRepo(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(long id) {
        return userRepo.getOne(id);
    }

    @Override
    public User findByDiscordId(String discordId) {
        return userRepo.findByDiscordId(discordId);
    }

    @Override
    public List<User> findAll() {
        List<User> users;

        users = userRepo.findAll();

        return users;
    }

    @Override
    public User save(User user) {
        if (user != null) {
            return userRepo.save(user);
        }

        return null;
    }

    @Override
    public boolean delete(User user) {
        if (user != null) {
            userRepo.deleteById(user.getId());
            return true;
        }

        return false;
    }
}
