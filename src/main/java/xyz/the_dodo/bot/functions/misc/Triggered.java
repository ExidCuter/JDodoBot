package xyz.the_dodo.bot.functions.misc;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.ImageUtils;

public class Triggered extends IFunction {
    public Triggered(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        try {
            p_messageParams.getTextChannel()
                    .sendFile(ImageUtils.generateTriggered(p_messageParams.getUser().getAvatarUrl()).toByteArray(), "triggered.gif").queue();
        } catch (Exception e) {
            p_messageParams.getTextChannel().sendMessage("There was an error with image creation.").queue();
        }
    }
}
