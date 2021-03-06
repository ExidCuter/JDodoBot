package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.IMultipleStage;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.types.message.Stage;
import xyz.the_dodo.bot.types.message.StageStatus;
import xyz.the_dodo.bot.utils.BannedUtils;
import xyz.the_dodo.bot.utils.PrefixUtils;

import java.util.HashMap;
import java.util.Map;

import static xyz.the_dodo.bot.listeners.StatsListener.userInteractions;
import static xyz.the_dodo.config.CommandConfig.commands;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.isFromType(ChannelType.TEXT) && BannedUtils.isUserBannedOnServer(event.getAuthor(), event.getGuild())) {
            return;
        }

        String prefix;
        MessageParams messageParams = new MessageParams(event.getMessage());

        if (event.isFromType(ChannelType.TEXT) && PrefixUtils.guildHasCustomPrefix(event.getGuild())) {
            prefix = PrefixUtils.prefixService.getByServerDiscordId(event.getGuild().getId()).getPrefix();
        } else {
            prefix = "!";
        }

        if (!prefix.equals("!") && messageParams.getCommand().startsWith("!")) {
            messageParams.getTextChannel().sendMessage("Your guild's prefix is set to `" + prefix + "`!").queue();
            return;
        }

        if (!messageParams.getCommand().startsWith(prefix)) {
            return;
        }

        IFunction command = commands.get(messageParams.getCommand().replace(prefix, ""));

        if (command != null) {
            if (command instanceof IMultipleStage) {
                Stage stage = Stage.builder()
                        .user(event.getAuthor())
                        .status(StageStatus.ACTIVE)
                        .orgMessage(messageParams)
                        .command((IMultipleStage) command)
                        .build();

                StageListener.registerUserStage(stage);
            }

            command.prepare(messageParams).trigger();

            userInteractions.put(messageParams.getUser().getId(), (userInteractions.containsKey(messageParams.getUser().getId())) ? userInteractions.get(messageParams.getUser().getId()) + 1 : 1);
        } else {
            messageParams.getTextChannel().sendMessage("Command: `" + messageParams.getMessage().getContentDisplay() + "` does not exists!\nType: `" + prefix + "help` for help!").queue();
        }
    }
}
