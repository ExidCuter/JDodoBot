package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.config.BotConfig;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.Stats;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatsListener extends ListenerAdapter {
    private static BotConfig config = BeanUtils.getBean(BotConfig.class);

    public static LinkedHashMap<String, Integer> userInteractions = new LinkedHashMap<String, Integer>() {
        @Override
        protected boolean removeEldestEntry(final Map.Entry eldest) {
            return size() > config.getMaxMessagesCached();
        }
    };

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
            server = ServerUtils.serverService.findByDiscordId(guild.getId());

            if (server.isSaveDeleted()) {
                DeleteListener.deletedMessages.put(event.getMessage().getId(), event.getMessage());
                //TODO: GET FILES
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
