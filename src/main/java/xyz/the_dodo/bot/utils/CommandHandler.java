package xyz.the_dodo.bot.utils;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.Functions.MainFunction;
import xyz.the_dodo.bot.Functions.misc.Hi;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler
{
	public static List<IFunction> commands = new ArrayList<>();

	public static void registerCommands(){
		commands.add(new Hi("hi", "Says helo", "hi"));
	}
}
