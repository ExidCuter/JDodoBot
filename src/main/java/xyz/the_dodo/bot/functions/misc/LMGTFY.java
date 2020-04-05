package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;

import java.net.URLEncoder;

@BotService(command = "lmgtfy", description = "Googles stuff for ya!", usage = "lmgtfy + <QUERY>")
public class LMGTFY extends IFunction {
    public LMGTFY(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        MessageChannel channel = messageParams.getTextChannel();
        try {
            if (messageParams.getParameters().length > 0)
                channel.sendMessage("http://lmgtfy.com/?q=" + URLEncoder.encode(messageParams.getContent(), "UTF-8")).queue();
            else
                channel.sendMessage(getUsage()).complete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
