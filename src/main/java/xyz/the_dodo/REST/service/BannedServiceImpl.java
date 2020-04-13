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
    private IBannedUserRepo bannedUserRepo;

    public void setBannedUserRepo(IBannedUserRepo bannedUserRepo) {
        this.bannedUserRepo = bannedUserRepo;
    }

    @Override
    public List<BannedUser> findByServerDiscordId(String discordId) {
        List<BannedUser> banned;

        banned = bannedUserRepo.findAll();

        return banned.stream()
                .filter(bannedUser -> bannedUser.getServer().getDiscordId().equals(discordId))
                .collect(Collectors.toList());
    }

    @Override
    public List<BannedUser> findByUserDiscordId(String discordId) {
        List<BannedUser> banned;

        banned = bannedUserRepo.findAll();

        return banned.stream()
                .filter(bannedUser -> bannedUser.getUser().getDiscordId().equals(discordId))
                .collect(Collectors.toList());
    }

    @Override
    public BannedUser findByUserAndServerDiscordId(String userDiscordId, String serverDiscordId) {
        List<BannedUser> banned;

        banned = bannedUserRepo.findAll();

        banned = banned.stream()
                .filter(bannedUser -> bannedUser.getUser().getDiscordId().equals(userDiscordId) && bannedUser.getServer().getDiscordId().equals(serverDiscordId))
                .collect(Collectors.toList());

        if (banned.size() > 0) {
            return banned.get(0);
        }

        return null;
    }

    @Override
    public BannedUser findById(long id) {
        return bannedUserRepo.getOne(id);
    }

    @Override
    public List<BannedUser> findAll() {
        return bannedUserRepo.findAll();
    }

    @Override
    public BannedUser save(BannedUser bannedUser) {
        if (bannedUser != null)
            return bannedUserRepo.save(bannedUser);

        return null;
    }

    @Override
    public boolean delete(BannedUser bannedUser) {
        if (bannedUser != null) {
            bannedUserRepo.deleteById(bannedUser.getId());
            return true;
        }

        return false;
    }
}
