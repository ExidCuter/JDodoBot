package xyz.the_dodo.bot.Functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.Functions.MainFunction;

public class Hi extends MainFunction
{
	public Hi(String command, String description, String usage)
	{
		super(command, description, usage);
	}

	@Override
	public void trigger(Message message)
	{
		User author;
		MessageChannel messageChannel;

		try {
			author = message.getAuthor();
			messageChannel = message.getTextChannel();

			messageChannel.sendMessage("Hi, " + author.getAsMention()).queue();
		} catch(Exception e){
			e.printStackTrace();
			//TODO: implement BugReport
		}
	}
}
