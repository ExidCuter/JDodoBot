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
        if (event.getAuthor().isBot())
            return;

        if(event.isFromType(ChannelType.TEXT) && BannedUtils.isUserBannedOnServer(event.getAuthor(), event.getGuild()))
            return;

        String prefix;
        MessageParams messageParams;

        messageParams = new MessageParams(event.getMessage());

        if (event.isFromType(ChannelType.TEXT) && PrefixUtils.guildHasCustomPrefix(event.getGuild()))
            prefix = PrefixUtils.m_prefixService.getByServerDiscordId(event.getGuild().getId()).getPrefix();
        else
            prefix = "!";

        commands.forEach(command -> {
            if (messageParams.getCommand().equalsIgnoreCase(prefix + command.getCommand())) {
                //try {
                    command.trigger(messageParams);
               /* } catch (Exception e) {
                    if (event.isFromType(ChannelType.PRIVATE))
                        event.getAuthor().openPrivateChannel().queue(p_privateChannel -> p_privateChannel.sendMessage("Can't do this over DMs!").queue());
                }*/

                userInteractions.put(messageParams.getUser().getId(),
                        (userInteractions.containsKey(messageParams.getUser().getId())) ? userInteractions.get(messageParams.getUser().getId()) + 1 : 1);
            }
        });
    }
}
