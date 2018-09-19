package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.DeletedMessage;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.Stats;

public class StatsListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user;
        Stats stats;
        Guild guild;
        Server server;

        if (!event.isFromType(ChannelType.TEXT))
            return;

        if (event.getAuthor().isBot())
            return;

        user = event.getAuthor();
        guild = event.getGuild();
        stats = StatsUtils.statsExists(user);

        if (ServerUtils.serverExist(guild)) {
            server = ServerUtils.m_serverService.findByDiscordId(guild.getId());

            if (server.isSaveDeleted()) {
                DeleteListener.deletedMessages.put(event.getMessage().getId(), event.getMessage());
            }
        }

        if (stats != null) {
            stats.setNumOfMessages(stats.getNumOfMessages() + 1);

            if (event.getMessage().getAttachments().size() > 0) {
                stats.setNumOfFiles(stats.getNumOfFiles() + event.getMessage().getAttachments().size());
            }

            StatsUtils.saveStats(stats);
        }
    }
}
