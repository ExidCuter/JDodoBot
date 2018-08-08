package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.bot.utils.CommandHandler;
import xyz.the_dodo.bot.utils.StringUtils;

import static xyz.the_dodo.bot.utils.CommandHandler.commands;

public class CommandListner extends ListenerAdapter
{
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if (event.getAuthor().isBot())
			return;

		String prefix;
		Message message;
		String content;
		String[] commandsNParameters;

		prefix = "!";
		message = event.getMessage();
		content = message.getContentRaw();
		commandsNParameters = StringUtils.getCommandNParameters(content);


		commands.forEach(command -> {
			if(commandsNParameters[0].equals(prefix + command.getCommand()))
				command.trigger(message);
		});
	}
}
