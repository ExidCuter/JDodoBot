package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;

import java.awt.*;

@BotService(command = "about", description = "About bot", usage = "about", category = CommandCategoryEnum.UTILS)
public class About extends IFunction {
    public About(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        EmbedBuilder embMsg = new EmbedBuilder();

        embMsg.setTitle("About DodoBot", "https://github.com/ExidCuter/JDodoBot-2.0");
        embMsg.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");
        embMsg.setColor(new Color(0x13FF00));
        embMsg.addField("Version", config != null ? config.getVersion() : "TEST VERSION", true);
        embMsg.addField("By", "Dodo Dodović", true);
        embMsg.addField("Servers", config != null ? Integer.toString(config.getNumOfServers()) : "0", true);
        embMsg.addField("GitHub", "https://github.com/ExidCuter/JDodoBot-2.0", false);
        embMsg.addField("Donate", " https://www.paypal.me/DodoDodovic", false);

        this.responseQueue.add(new BotResponse(BotResponseTypeEnum.EMBED, embMsg.build(), messageParams.getTextChannel()));

        return this;
    }
}
