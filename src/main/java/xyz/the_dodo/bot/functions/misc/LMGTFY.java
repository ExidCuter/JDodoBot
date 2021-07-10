package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.api.entities.MessageChannel;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;

import java.net.URLEncoder;

@BotService(command = "lmgtfy", description = "Googles stuff for ya!", usage = "lmgtfy + <QUERY>")
public class LMGTFY extends IFunction {
    public LMGTFY(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        MessageChannel channel = messageParams.getTextChannel();
        try {
            if (messageParams.getParameters().length > 0)
                channel.sendMessage("http://lmgtfy.com/?q=" + URLEncoder.encode(messageParams.getContent(), "UTF-8")).queue();
            else
                channel.sendMessage(getUsage()).complete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return this;
    }
}
