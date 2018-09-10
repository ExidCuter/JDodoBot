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

        if (event.getAuthor().isBot())
            return;

        user = event.getAuthor();
        stats = StatsUtils.statsExists(user);

        if (stats != null) {
            stats.setNumOfMessages(stats.getNumOfMessages() + 1);

            if (event.getMessage().getAttachments().size() > 0) {
                stats.setNumOfFiles(stats.getNumOfFiles() + event.getMessage().getAttachments().size());
            }

            StatsUtils.saveStats(stats);
        }
    }


}
