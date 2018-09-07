package xyz.the_dodo.bot.utils;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.Functions.bank.*;
import xyz.the_dodo.bot.Functions.misc.*;
import xyz.the_dodo.bot.Functions.quotes.CreateQuote;
import xyz.the_dodo.bot.Functions.quotes.GetAllQuotes;
import xyz.the_dodo.bot.Functions.quotes.GetBadQuote;
import xyz.the_dodo.bot.Functions.quotes.GetQuote;
import xyz.the_dodo.bot.Functions.stats.Bribe;
import xyz.the_dodo.bot.Functions.stats.CheckStats;
import xyz.the_dodo.bot.Functions.stats.CountStats;
import xyz.the_dodo.bot.Functions.stats.ResetStats;
import xyz.the_dodo.bot.Functions.utils.*;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler
{
	public static List<IFunction> commands = new ArrayList<>();

	public static void registerCommands() {
		//misc
		commands.add(new Hi("hi", "Says helo", "hi"));
		commands.add(new Cat("cat", "Gets a picture of a cat!", "cat"));
		commands.add(new CoinFlip("coinFlip", "Flips a coin", "coinFlip"));
		commands.add(new CopyPasta("copypasta", "COPYPASTA", "copypasta"));
		commands.add(new LMGTFY("lmgtfy", "Googles stuff for ya!", "lmgtfy + <QUERY>"));
		commands.add(new Magic8Ball("8ball", "Magic8Ball will answer all of your yes/no questions", "8ball"));
		commands.add(new Triggered("triggered", "Show how triggered are you", "triggered"));
		commands.add(new Roll("roll", "rolls a X sided dice", "roll <MAX>"));
		commands.add(new Shoot("shoot", "Shoots ya", "shoot/shoot <USER MENTION>"));
		commands.add(new Speak("say", "Repeats after you", "say <WHAT>"));
		commands.add(new Meme("meme", "Gets a random meme from /r/dankmemes", "meme"));
		commands.add(new RedditRandomPost("reddit.random", "Gets random post from hot section of the specified subreddit!", "reddit.random <SUBREDDIT NAME>"));
		commands.add(new RedditTopPosts("reddit.getTop", "Gets top 3 posts form hot from specified subreddit!", "reddit.getTop <SUBREDDIT NAME>"));
		commands.add(new ProgrammerMeme("reddit.getMeme();", "Gets a meme from r/programmerHumor", "reddit.getMeme();"));
		commands.add(new GiphyGif("gif", "Gets a GIF from Giphy", "gif <QUERY>"));
		commands.add(new FortniteRecords("fortnite", "Fortnite records!", "!fortnite <PLATFORM> <GAMEMODE> <USERNAME>"));

		//utils
		commands.add(new SetAdmin("setAdmin", "Sets admin of the guild", "setAdmin <USER MENTION>"));
		commands.add(new GetAdmins("getAdmins", "Returns the list of admins", "admins"));
		commands.add(new DeleteAdmin("delAdmin", "Removes user from the admin list", "delAdmin <MENTION USERS>"));
		commands.add(new Help("help", "Shows help", "help || help <command>"));
		commands.add(new SetDefaultRole("setDefaultRole", "When a new user joins your guild they are set to this role!", "setDefaultRole <ROLE MENTION>"));
		commands.add(new GetDefaultRole("getDefaultRole", "Gets the name of default role", "getDefaultRole"));
		commands.add(new About("about", "About bot", "about"));

		//bank
		commands.add(new Register("bank.register", "Creates a bank account", "bank.register"));
		commands.add(new Balance("bank.balance", "Gets balance of your bank account", "bank.balance"));
		commands.add(new PayDay("payday", "PAYDAY!!!", "payday"));
		commands.add(new Transfer("bank.transfer", "Transfers money!!", "bank.transfer <USER MENTION/ACCOUNT NUMBER> <AMOUNT>"));
		commands.add(new Slot("slot", "Play the slots!!", "slot <AMOUNT>"));

		//stats
		commands.add(new CountStats("countStats", "Starts tracking your stats", "countStats"));
		commands.add(new CheckStats("stats", "Shows your stats", "stats"));
		commands.add(new ResetStats("stopStats", "Stops tracking your stats", "stopStats"));
		commands.add(new Bribe("bribe", "Bribes the bot. Increases your stats score!", "bribe <AMOUNT OF MONEY>"));

		//quotes
		commands.add(new CreateQuote("createQuote", "Creates a quote", "quote <WHO> <QUOTE>"));
		commands.add(new GetQuote("quote", "Gets a quote from person", "quote <PERSON>"));
		commands.add(new GetBadQuote("quote", "Gets a quote from reddit", "quote"));
		commands.add(new GetAllQuotes("allQuotes", "Gets all of the persons quotes", "allQuotes <PERSON>"));
	}

	public static String generateHelp() {
		StringBuilder builder;

		builder = new StringBuilder();

		builder.append("DodoBot functions:\n");

		commands.forEach(command -> builder.append(command.getHelp() + "\n"));

		builder.append("`type help <command> to get more help`");

		return builder.toString();
	}
}
