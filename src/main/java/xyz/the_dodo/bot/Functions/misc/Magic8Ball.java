package xyz.the_dodo.bot.Functions.misc;

import net.dv8tion.jda.core.entities.Message;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;

public class Magic8Ball extends IFunction
{
	private String[] answers = {
			"Yes definitely",
			"You may rely on it",
			"As I see it, yes",
			"Most likely",
			"Outlook good",
			"Yes",
			"You know it NIGGA",
			"You know it NIGGA",
			"Signs point to yes",
			"Reply hazy try again",
			"It is certain",
			"It is decidedly so",
			"Without a doubt",
			"Ask again later",
			"You know it NIGGA",
			"Better not tell you now",
			"Cannot predict now",
			"Concentrate and ask again",
			"Don't count on it",
			"My reply is no",
			"You know it NIGGA",
			"My sources say no",
			"Outlook not so good",
			"Very doubtful"
	};

	public Magic8Ball(String command, String description, String usage)
	{
		super(command, description, usage);
	}

	@Override
	public void trigger(MessageParams p_messageParams)
	{
		try {
			p_messageParams.getTextChannel().sendMessage(answers[RandomGen.rndNm(answers.length)]).complete();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}