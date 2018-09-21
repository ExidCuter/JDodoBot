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
    private ISubRepo m_subRepo;

    public void setSubRepo(ISubRepo p_subRepo) {
        m_subRepo = p_subRepo;
    }

    @Override
    public Subscription findById(long id) {
        return m_subRepo.getOne(id);
    }

    @Override
    public List<Subscription> findAll() {
        return m_subRepo.findAll();
    }

    @Override
    public Subscription save(Subscription object) {
        if (object != null)
            return m_subRepo.save(object);

        return null;
    }

    @Override
    public boolean delete(Subscription object) {
        if (object != null) {
            m_subRepo.delete(object);
            return true;
        }

        return false;
    }

    @Override
    public List<Subscription> getAllSubscriptionOfServerDiscordId(String discordId) {
        return m_subRepo.findAll()
                .stream()
                .filter(p_subscription -> p_subscription.getServer().getDiscordId().equals(discordId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> getSubscriptionsToTrigger(int tick) {
        return  m_subRepo.findAll()
                .stream()
                .filter(p_subscription ->  tick % p_subscription.getTimer() == 0)
                .collect(Collectors.toList());
    }
}
