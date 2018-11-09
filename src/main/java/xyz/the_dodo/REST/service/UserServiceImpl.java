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
    private IUserRepo m_userRepo;

    public void setUserRepo(IUserRepo p_userRepo)
    {
        m_userRepo = p_userRepo;
    }

    @Override
    public User findById(long id) {
        return m_userRepo.getOne(id);
    }

    @Override
    public User findByDiscordId(String discordId)
    {
        return m_userRepo.findByDiscordId(discordId);
    }

    @Override
    public List<User> findAll() {
        List<User> users;

        users = m_userRepo.findAll();

        return users;
    }

    @Override
    public User save(User user) {
        if (user != null) {
            return m_userRepo.save(user);
        }
        return null;
    }

    @Override
    public boolean delete(User user) {
        if (user != null){
            m_userRepo.deleteById(user.getId());
            return true;
        }
        return false;
    }
}
