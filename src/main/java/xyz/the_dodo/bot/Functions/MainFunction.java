package xyz.the_dodo.bot.Functions;

import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public abstract class MainFunction implements xyz.the_dodo.bot.Functions.IFunction
{
	public String command;
	public String description;
	public String usage;
	public boolean isService;

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String p_command)
	{
		command = p_command;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String p_description)
	{
		description = p_description;
	}

	public String getUsage()
	{
		return usage;
	}

	public void setUsage(String p_usage)
	{
		usage = p_usage;
	}

	public boolean isService()
	{
		return isService;
	}

	public void setService(boolean p_service)
	{
		isService = p_service;
	}

	public MainFunction(String command, String description, String usage)
	{
		this.usage = usage;
		this.description = description;
		this.command = command;
	}

	@Override
	public String getHelp()
	{
		return "```Command: " + command +
				"\nDescription: " + description +
				"\nUsage: " + usage + "```";
	}

	@Override
	public EmbedBuilder getEmbededHelp()
	{
		EmbedBuilder embMsg = new EmbedBuilder();
		embMsg.setTitle("Command: " + command + "", "http://the-dodo.xyz")
				.setDescription(description)
				.setColor(new Color(0x21FF00))
				.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg")
				.setAuthor("DodoBot help", "http://the-dodo.xyz", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg")
				.addField("Usage:", usage, false);
		return embMsg;
	}
}
