package xyz.the_dodo.bot.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.bank.*;
import xyz.the_dodo.bot.functions.misc.*;
import xyz.the_dodo.bot.functions.quotes.*;
import xyz.the_dodo.bot.functions.stats.*;
import xyz.the_dodo.bot.functions.utils.*;
import xyz.the_dodo.bot.functions.voice.*;
import xyz.the_dodo.bot.types.CommandCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandHandler {
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
        commands.add(new Shoot("shoot", "Shoots ya", "shoot/shoot <#USER MENTION>"));
        commands.add(new Speak("say", "Repeats after you", "say <WHAT>"));
        commands.add(new Meme("meme", "Gets a random meme from /r/dankmemes", "meme"));
        commands.add(new RedditRandomPost("reddit.random", "Gets random post from hot section of the specified subreddit!", "reddit.random <SUBREDDIT NAME>"));
        commands.add(new RedditTopPosts("reddit.getTop", "Gets top 3 posts form hot from specified subreddit!", "reddit.getTop <SUBREDDIT NAME>"));
        commands.add(new ProgrammerMeme("reddit.getMeme();", "Gets a meme from r/programmerHumor", "reddit.getMeme();"));
        commands.add(new GiphyGif("gif", "Gets a GIF from Giphy", "gif <QUERY>"));
        commands.add(new FortniteRecords("fortnite", "Fortnite records!", "!fortnite <PLATFORM> <GAMEMODE> <USERNAME>"));
        commands.add(new FortniteRecordsImage("fortnite.img", "Fortnite records IMAGE!!!", "!fortnite.img <PLATFORM> <GAMEMODE> <USERNAME>"));

        //utils
        commands.add(new SetAdmin("setAdmin", "Sets admin of the guild", "setAdmin <USER MENTION>"));
        commands.add(new GetAdmins("getAdmins", "Returns the list of admins", "admins"));
        commands.add(new DeleteAdmin("delAdmin", "Removes user from the admin list", "delAdmin <MENTION USERS>"));
        commands.add(new Help("help", "Shows help", "help <#COMMAND>"));
        commands.add(new SetDefaultRole("setDefaultRole", "When a new user joins your guild they are set to this role!", "setDefaultRole <ROLE MENTION>"));
        commands.add(new GetDefaultRole("getDefaultRole", "Gets the name of default role", "getDefaultRole"));
        commands.add(new About("about", "About bot", "about"));
        commands.add(new BanUser("banUser", "Bans an user or multiple users", "banUser <MENTION USERS TO BAN>"));
        commands.add(new UnbanUser("unbanUser", "Unbans an user or multiple users", "unbanUser <MENTION USERS TO BAN>"));
        commands.add(new GetBannedUsers("getBannedUsers", "Gets the list of all the banned user", "getBannedUsers"));
        commands.add(new SetDeletedMessages("trackDeleted", "Tracks deleted messages of this guild!", "trackDeleted <TRUE/FALSE>"));
        commands.add(new GetDeletedMessages("getDeleted", "Gets deleted messages from this guild or a specific user of this guild!", "getDeleted <#MENTION> <#AMOUNT>"));
        commands.add(new Notification("notifyAll", "Sends a notification to all servers! Only bot owner can use this commad!", "notifyAll <MESSAGE>"));
        commands.add(new SetCustomPrefix("setPrefix", "Sets a custom prefix for your guild", "setPrefix <PREFIX>"));
        commands.add(new Subscribe("sub", "Subscribes to a command and triggers it at an interval.", "sub <interval> <command> ([sub 3 shoot @person)] - triggers \"shoot\" once every 30 minutes)"));
        commands.add(new Unsubscribe("unsub", "Unsubscribes you from a command.", "unsub <ID>"));
        commands.add(new GetSubscriptions("getSubs", "Displays all subscriptions of your guild.", "getSubs"));

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

        //voice
        commands.add(new Join("join", "Joins a voice channel", "join <CHANNEL NAME/CHANNEL ID>"));
        commands.add(new Leave("leave", "Leaves voice channel", "leave"));
        commands.add(new Play("play", "Plays a song or resumes playing", "play || play <SONG LINK>"));
        commands.add(new Pause("pause", "Pauses the player",""));
        commands.add(new Stop("stop", "Stops playing and clears the queue", "stop"));
        commands.add(new Skip("skip", "Skips current item in queue", "skip"));
        commands.add(new Restart("restart", "Restarts playing current song/video", "restart"));
        commands.add(new Shuffle("shuffle", "Shuffles the playing queue", "shuffle"));
        commands.add(new Repeat("repeat", "Repeats current song/video", "repeat"));
        commands.add(new Volume("volume", "Changes the volume of the player", "volume <10-100>"));
        commands.add(new NowPlaying("nowPlaying", "Displays what is currently playing", "nowPlaying"));
        commands.add(new ListQueue("listPlaying", "Lists all the items in queue!", "listPlaying"));
        commands.add(new Reset("reset", "Resets the player", "reset"));
    }

    public static String generateHelp() {
        StringBuilder builder;

        builder = new StringBuilder();

        builder.append("DodoBot function Categories:\n");

        for (CommandCategory c : CommandCategory.values()) {
            if (c.equals(CommandCategory.ADMIN))
                continue;
            builder.append("\t`" + c.toString() + "`\n");
        }

        builder.append("`type help <CATEGORY> to get more help`");
        return builder.toString();
    }

    public static String generateHelp(String category) {
        StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Commands from category `" + category + "`\n");

        CommandHandler.commands.stream()
                .filter(command -> command.commandCategory.toString().equalsIgnoreCase(category))
                .forEach(command -> builder.append("\t" + command.getHelp() + "\n"));

        builder.append("`type help <command> to get more help`");

        return builder.toString();
    }
}
