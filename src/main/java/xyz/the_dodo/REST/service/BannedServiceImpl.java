package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IBannedUserRepo;
import xyz.the_dodo.database.interfaces.services.IBannedService;
import xyz.the_dodo.database.types.BannedUser;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannedServiceImpl implements IBannedService {
    @Autowired
    private IBannedUserRepo m_bannedUserRepo;

    @Override
    public List<BannedUser> findByServerDiscordId(String discordId) {
        List<BannedUser> banned;

        banned = m_bannedUserRepo.findAll();

        return banned.stream()
                .filter(p_bannedUser -> p_bannedUser.getServer().getDiscordId().equals(discordId))
                .collect(Collectors.toList());
    }

    @Override
    public List<BannedUser> findByUserDiscordId(String discordId) {
        List<BannedUser> banned;

        banned = m_bannedUserRepo.findAll();

        return banned.stream()
                .filter(p_bannedUser -> p_bannedUser.getUser().getDiscordId().equals(discordId))
                .collect(Collectors.toList());
    }

    @Override
    public BannedUser findByUserAndServerDiscordId(String userDiscordId, String serverDiscordId) {
        List<BannedUser> banned;

        banned = m_bannedUserRepo.findAll();

        banned = banned.stream()
                .filter(p_bannedUser -> p_bannedUser.getUser().getDiscordId().equals(userDiscordId) && p_bannedUser.getServer().getDiscordId().equals(serverDiscordId))
                .collect(Collectors.toList());

        if (banned.size() > 0) {
            return banned.get(0);
        }

        return null;
    }

    public void setBannedUserRepo(IBannedUserRepo p_bannedUserRepo) {
        m_bannedUserRepo = p_bannedUserRepo;
    }

    @Override
    public BannedUser findById(long id) {
        return m_bannedUserRepo.getOne(id);
    }

    @Override
    public List<BannedUser> findAll() {
        return m_bannedUserRepo.findAll();
    }

    @Override
    public BannedUser save(BannedUser object) {
        if (object != null)
            return m_bannedUserRepo.save(object);

        return null;
    }

    @Override
    public BannedUser save(BannedUser oldObject, BannedUser newObject) {
        return null;
    }

    @Override
    public boolean delete(BannedUser object) {
        if (object != null) {
            m_bannedUserRepo.deleteById(object.getId());
            return true;
        }

        return false;
    }
}
