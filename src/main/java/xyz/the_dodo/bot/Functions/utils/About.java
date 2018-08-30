package xyz.the_dodo.bot.Functions.utils;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;

import java.awt.*;

public class About extends IFunction {
    public About(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        EmbedBuilder embMsg = new EmbedBuilder();
        embMsg.setTitle("About DodoBot", "https://github.com/ExidCuter/JDodoBot");
        embMsg.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");
        embMsg.setColor(new Color(0x13FF00));
        embMsg.addField("Version", DodoBot.verzion, true);
        embMsg.addField("By", "Dodo Dodović", true);
        embMsg.addField("Servers", Integer.toString(DodoBot.getNumOfServers()), true);
        embMsg.addField("GitHub", "https://github.com/ExidCuter/JDodoBot", false);
        embMsg.addField("Donate", " https://www.paypal.me/DodoDodovic", false);
        p_messageParams.getTextChannel().sendMessage(embMsg.build()).complete();
    }
}
