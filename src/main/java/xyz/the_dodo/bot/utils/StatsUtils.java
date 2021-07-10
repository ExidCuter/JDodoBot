package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.api.entities.User;
import xyz.the_dodo.REST.service.StatsServiceImpl;
import xyz.the_dodo.config.BotConfig;
import xyz.the_dodo.database.interfaces.services.IStatsService;
import xyz.the_dodo.database.types.Stats;

import java.util.ArrayList;
import java.util.List;

public class StatsUtils {
    private static int numOfMessages = 0;
    private static List<Stats> statsQueue = new ArrayList<>();
    public static IStatsService statsService = BeanUtils.getBean(StatsServiceImpl.class);
    public static BotConfig config = BeanUtils.getBean(BotConfig.class);

    public static Stats statsExists(User user) {
        //TODO: optimise more! (to many reads)
        Stats stats;

        stats = null;

        for (Stats statsInQueue : statsQueue) {
            if (statsInQueue.getUser().getDiscordId().equals(user.getId())) {
                stats = statsInQueue;
            }
        }

        if (stats == null) {
            stats = statsService.getByUserDiscordId(user.getId());
        }

        return stats;
    }

    public static void saveStats(Stats stats) {
        boolean found;

        found = false;

        for (Stats statsInQueue : statsQueue) {
            if (statsInQueue.getUser().getDiscordId().equals(stats.getUser().getDiscordId())) {
                found = true;
                break;
            }
        }

        if (!found) {
            statsQueue.add(stats);
        }

        numOfMessages++;

        if (numOfMessages > config.getNumOfServers() * 2) {
            flushStatsQueue();

            numOfMessages = 0;
        }
    }

    public static void flushStatsQueue() {
        statsQueue.forEach(stats -> statsService.save(stats));

        statsQueue.clear();
    }
}
