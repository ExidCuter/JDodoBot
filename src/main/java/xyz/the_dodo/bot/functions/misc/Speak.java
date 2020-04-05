package xyz.the_dodo.bot.functions.misc;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;

@BotService(command = "say", description = "Repeats after you", usage = "say <WHAT>")
public class Speak extends IFunction {
    public Speak(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        String message;

        if (messageParams.getParameters().length > 0) {
            message = messageParams.getContent().substring(0, 1).toUpperCase() + messageParams.getContent().substring(1);

            if (!message.endsWith("."))
                message += ".";

            messageParams.getTextChannel().sendMessage(message).queue();
        }
    }
}
