package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.ISubRepo;
import xyz.the_dodo.database.interfaces.services.ISubService;
import xyz.the_dodo.database.types.Subscription;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubServiceImpl implements ISubService {
    @Autowired
    private ISubRepo subRepo;

    public void setSubRepo(ISubRepo subRepo) {
        this.subRepo = subRepo;
    }

    @Override
    public Subscription findById(long id) {
        return subRepo.getOne(id);
    }

    @Override
    public List<Subscription> findAll() {
        return subRepo.findAll();
    }

    @Override
    public Subscription save(Subscription subscription) {
        if (subscription != null)
            return subRepo.save(subscription);

        return null;
    }

    @Override
    public boolean delete(Subscription subscription) {
        if (subscription != null) {
            subRepo.delete(subscription);
            return true;
        }

        return false;
    }

    @Override
    public List<Subscription> getAllSubscriptionOfServerDiscordId(String discordId) {
        return subRepo.findAll()
                .stream()
                .filter(subscription -> subscription.getServer().getDiscordId().equals(discordId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> getSubscriptionsToTrigger(int tick) {
        return subRepo.findAll()
                .stream()
                .filter(subscription -> tick % subscription.getTimer() == 0)
                .collect(Collectors.toList());
    }
}
