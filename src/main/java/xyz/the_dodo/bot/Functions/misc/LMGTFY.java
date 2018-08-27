package xyz.the_dodo.bot.Functions.misc;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

import java.net.URLEncoder;

public class LMGTFY extends IFunction
{
	public LMGTFY(String command, String description, String usage) {
		super(command, description, usage);
	}

	@Override
	public void trigger(MessageParams p_messageParams) {
		MessageChannel channel = p_messageParams.getTextChannel();
		try {
			if (p_messageParams.getParameters().length > 0)
				channel.sendMessage("http://lmgtfy.com/?q=" + URLEncoder.encode(p_messageParams.getContent(), "UTF-8")).queue();
			else
				channel.sendMessage(getUsage()).complete();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
