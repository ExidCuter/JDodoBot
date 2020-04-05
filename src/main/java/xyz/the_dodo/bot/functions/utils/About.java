package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;

import java.awt.*;

@BotService(command = "about", description = "About bot", usage = "about", category = CommandCategoryEnum.UTILS)
public class About extends IFunction {
    public About(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        EmbedBuilder embMsg = new EmbedBuilder();

        embMsg.setTitle("About DodoBot", "https://github.com/ExidCuter/JDodoBot-2.0");
        embMsg.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");
        embMsg.setColor(new Color(0x13FF00));
        embMsg.addField("Version", DodoBot.version, true);
        embMsg.addField("By", "Dodo Dodović", true);
        embMsg.addField("Servers", Integer.toString(DodoBot.getNumOfServers()), true);
        embMsg.addField("GitHub", "https://github.com/ExidCuter/JDodoBot-2.0", false);
        embMsg.addField("Donate", " https://www.paypal.me/DodoDodovic", false);

        messageParams.getTextChannel().sendMessage(embMsg.build()).complete();
    }
}
