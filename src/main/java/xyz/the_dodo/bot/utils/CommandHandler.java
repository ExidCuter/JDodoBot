package xyz.the_dodo.bot.utils;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.Functions.misc.*;
import xyz.the_dodo.bot.Functions.utils.SetAdmin;

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
		commands.add(new LMGTFY("lmgtfy", "Googles stuff for ya!", "lmgtfy + <QUERY>"));
		commands.add(new Magic8Ball("8ball", "Magic8Ball will answer all of your yes/no questions", "8ball"));
		commands.add(new Triggered("triggered", "Show how triggered are you", "triggered"));
		commands.add(new Roll("roll", "rolls a X sided dice", "roll <MAX>"));
		commands.add(new SetAdmin("setAdmin", "Sets admin of the guild", "setAdmin <USER MENTION>"));
	}
}
