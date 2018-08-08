package xyz.the_dodo.bot.utils;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.Functions.misc.Cat;
import xyz.the_dodo.bot.Functions.misc.CoinFlip;
import xyz.the_dodo.bot.Functions.misc.CopyPasta;
import xyz.the_dodo.bot.Functions.misc.Hi;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler
{
	public static List<IFunction> commands = new ArrayList<>();

	public static void registerCommands() {
		commands.add(new Hi("hi", "Says helo", "hi"));
		commands.add(new Cat("cat", "Gets a picture of a cat!", "cat"));
		commands.add(new CoinFlip("coinFlip", "Flips a coin", "coinFlip"));
		commands.add(new CopyPasta("copypasta", "COPYPASTA", "copypasta"));
	}
}
