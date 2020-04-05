package xyz.the_dodo.bot.functions.misc;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.ImageUtils;

@BotService(command = "triggered", description = "Show how triggered are you", usage = "triggered")
public class Triggered extends IFunction {
    public Triggered(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        try {
            messageParams.getTextChannel()
                    .sendFile(ImageUtils.generateTriggered(messageParams.getUser().getAvatarUrl()).toByteArray(), "triggered.gif").queue();
        } catch (Exception e) {
            messageParams.getTextChannel().sendMessage("There was an error with image creation.").queue();
        }
    }
}
