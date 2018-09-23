package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;

public class Hi extends IFunction {
    public Hi(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        MessageChannel messageChannel;

        messageChannel = p_messageParams.getTextChannel();

        messageChannel.sendMessage("Hi, " + p_messageParams.getUser().getAsMention()).queue();
    }
}
