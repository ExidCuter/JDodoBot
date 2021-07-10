package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.DeletedMessageUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.config.BotConfig;
import xyz.the_dodo.database.types.DeletedMessage;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class DeleteListener extends ListenerAdapter {
    private static BotConfig config = BeanUtils.getBean(BotConfig.class);

    static LinkedHashMap<String, Message> deletedMessages = new LinkedHashMap<String, Message>() {
        @Override
        protected boolean removeEldestEntry(final Map.Entry eldest) {
            return size() > config.getMaxMessagesCached();
        }
    };

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
        User user;
        Server server;
        Message message;
        DeletedMessage deletedMessage;

        if (deletedMessages.containsKey(event.getMessageId())) {
            message = deletedMessages.get(event.getMessageId());

            if (!UserUtils.userExists(message.getAuthor())) {
                UserUtils.createDodoUser(message.getAuthor());
            }

            user = UserUtils.userService.findByDiscordId(message.getAuthor().getId());

            server = ServerUtils.serverService.findByDiscordId(message.getGuild().getId());

            deletedMessage = new DeletedMessage();

            deletedMessage.setServer(server);
            deletedMessage.setUser(user);
            deletedMessage.setMessage(message.getContentRaw());

            //TODO: GET FILES
            deletedMessage.setFileLocation("");

            DeletedMessageUtils.addDeletedMessage(deletedMessage);
        }
    }
}
