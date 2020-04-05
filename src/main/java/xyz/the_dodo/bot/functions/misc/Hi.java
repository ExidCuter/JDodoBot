package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;

@BotService(command = "hi", description = "Says helo", usage = "hi")
public class Hi extends IFunction {
    public Hi(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        MessageChannel messageChannel;

        messageChannel = messageParams.getTextChannel();

        messageChannel.sendMessage("Hi, " + messageParams.getUser().getAsMention()).queue();
    }
}
