package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.bot.types.MessageParams;

import static xyz.the_dodo.bot.utils.CommandHandler.commands;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        String prefix;
        MessageParams messageParams;

        prefix = "!";

        messageParams = new MessageParams(event.getMessage());

        commands.forEach(command -> {
            if (messageParams.getCommand().equals(prefix + command.getCommand()))
                command.trigger(messageParams);
        });
    }
}
