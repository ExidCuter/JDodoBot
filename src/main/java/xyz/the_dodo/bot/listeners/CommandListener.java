package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BannedUtils;
import xyz.the_dodo.bot.utils.PrefixUtils;

import static xyz.the_dodo.bot.types.CommandHandler.commands;
import static xyz.the_dodo.bot.listeners.StatsListener.userInteractions;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromType(ChannelType.TEXT))
            return;

        if (event.getAuthor().isBot())
            return;

        if(BannedUtils.isUserBannedOnServer(event.getAuthor(), event.getGuild()))
            return;

        String prefix;
        MessageParams messageParams;

        messageParams = new MessageParams(event.getMessage());

        if (PrefixUtils.guildHasCustomPrefix(event.getGuild()))
            prefix = PrefixUtils.m_prefixService.getByServerDiscordId(event.getGuild().getId()).getPrefix();
        else
            prefix = "!";

        commands.forEach(command -> {
            if (messageParams.getCommand().equalsIgnoreCase(prefix + command.getCommand())) {
                command.trigger(messageParams);

                userInteractions.put(messageParams.getUser().getId(),
                        (userInteractions.containsKey(messageParams.getUser().getId())) ? userInteractions.get(messageParams.getUser().getId()) + 1 : 1);
            }
        });
    }
}
