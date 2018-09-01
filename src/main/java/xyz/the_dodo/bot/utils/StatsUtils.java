package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.REST.service.StatsServiceImpl;
import xyz.the_dodo.database.interfaces.services.IStatsService;
import xyz.the_dodo.database.types.Stats;

import java.util.ArrayList;
import java.util.List;

public class StatsUtils {
    private static int numOfMessages = 0;
    private static List<Stats> m_statsQueue = new ArrayList<>();
    public static IStatsService m_statsService = BeanUtils.getBean(StatsServiceImpl.class);

    public static Stats statsExists(User p_user) {
        //TODO: optimise more! (to many reads)
        Stats stats;

        stats = null;

        for (Stats p_statsInQueue : m_statsQueue) {
            if (p_statsInQueue.getUser().getDiscordId().equals(p_user.getId())) {
                stats = p_statsInQueue;
            }
        }
        if (stats == null)
            stats = m_statsService.getByUserDiscordId(p_user.getId());

        return stats;
    }

    public static void saveStats(Stats p_stats) {
        boolean found;

        found = false;

        for (Stats p_statsInQueue : m_statsQueue) {
            if (p_statsInQueue.getUser().getDiscordId().equals(p_stats.getUser().getDiscordId())) {
                p_statsInQueue = p_stats;
                found = true;
            }
        }

        if (!found) {
            m_statsQueue.add(p_stats);
        }

        numOfMessages++;

        if (numOfMessages > DodoBot.getNumOfServers() * 2) {
            flushStatsQueue();

            numOfMessages = 0;
        }
    }

    public static void flushStatsQueue() {
        m_statsQueue.forEach(p_stats -> m_statsService.save(p_stats));

        m_statsQueue.clear();
    }
}
