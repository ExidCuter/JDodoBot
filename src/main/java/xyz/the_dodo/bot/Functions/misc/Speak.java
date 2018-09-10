package xyz.the_dodo.bot.Functions.misc;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;

public class Speak extends IFunction {
    public Speak(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        String message;
        if (p_messageParams.getParameters().length > 0) {
            message = p_messageParams.getContent().substring(0, 1).toUpperCase() + p_messageParams.getContent().substring(1);

            if (!message.endsWith("."))
                message += ".";

            p_messageParams.getTextChannel().sendMessage(message).queue();
        }
    }
}
