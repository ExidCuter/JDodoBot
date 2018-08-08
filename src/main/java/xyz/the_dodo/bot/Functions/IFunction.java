package xyz.the_dodo.bot.Functions;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

public interface IFunction
{
	void trigger(Message message);
	String getHelp();
	EmbedBuilder getEmbededHelp();
}
