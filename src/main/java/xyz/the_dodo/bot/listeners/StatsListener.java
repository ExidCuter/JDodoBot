package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.Stats;

public class StatsListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user;
        Stats stats;

        user = event.getAuthor();
        stats = StatsUtils.statsExists(user);

        if (stats != null) {
            stats.setNumOfMessages(stats.getNumOfMessages() + 1);

            StatsUtils.saveStats(stats);
        }
    }
}
