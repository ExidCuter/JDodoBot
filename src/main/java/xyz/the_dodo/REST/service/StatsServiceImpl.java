package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IStatsRepo;
import xyz.the_dodo.database.interfaces.services.IStatsService;
import xyz.the_dodo.database.types.Stats;

import java.util.List;

@Service
public class StatsServiceImpl implements IStatsService {
    @Autowired
    private IStatsRepo statsRepo;

    public void setStatsRepo(IStatsRepo statsRepo) {
        this.statsRepo = statsRepo;
    }

    @Override
    public Stats getByUserDiscordId(String discordId) {
        List<Stats> stats;

        stats = findAll();

        for (Stats stat : stats) {
            if (stat.getUser().getDiscordId().equals(discordId))
                return stat;
        }

        return null;
    }

    @Override
    public Stats findById(long id) {
        return statsRepo.getOne(id);
    }

    @Override
    public List<Stats> findAll() {
        return statsRepo.findAll();
    }

    @Override
    public Stats save(Stats stats) {
        if (stats != null) {
            return statsRepo.save(stats);
        }

        return null;
    }

    @Override
    public boolean delete(Stats stats) {
        if (stats != null) {
            statsRepo.deleteById(stats.getId());
            return true;
        }

        return false;
    }
}
