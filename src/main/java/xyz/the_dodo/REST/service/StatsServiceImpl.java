package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IStatsRepo;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IStatsService;
import xyz.the_dodo.database.types.Stats;

import java.util.List;

@Service
public class StatsServiceImpl implements IStatsService {
    @Autowired
    private IStatsRepo m_statsRepo;

    @Autowired
    private IUserRepo m_userRepo;

    public void setStatsRepo(IStatsRepo p_statsRepo) {
        m_statsRepo = p_statsRepo;
    }

    @Override
    public Stats getByUserDiscordId(String discordId) {
        List<Stats> stats;
        
        stats = findAll();

        for (Stats p_stats : stats) {
            if (p_stats.getUser().getDiscordId().equals(discordId))
                return p_stats;
        }

        return null;
    }

    @Override
    public Stats findById(long id) {
        return m_statsRepo.getOne(id);
    }

    @Override
    public List<Stats> findAll() {
        return m_statsRepo.findAll();
    }

    @Override
    public Stats save(Stats object) {
        if(object != null) {
            return m_statsRepo.save(object);
        }

        return null;
    }

    @Override
    public boolean delete(Stats object) {
        if(object != null) {
             m_statsRepo.deleteById(object.getId());
             return true;
        }

        return false;
    }
}
