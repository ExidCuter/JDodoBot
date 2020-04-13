package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

@BotService(command = "notifyAll", description = "Sends a notification to all servers! Only bot owner can use this commad!", usage = "notifyAll <MESSAGE>", category = CommandCategoryEnum.UTILS)
public class Notification extends IFunction {
    public Notification(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        String message;

        if (messageParams.getUser().getId().equals(DodoBot.botOwner)) {
            if (messageParams.getParameters().length > 0) {
                message = StringUtils.glueStringsBackTogether(messageParams.getParameters(), " ", 0);

                DodoBot.getGuilds().forEach(guild -> {
                    try {
                        guild.getDefaultChannel().sendMessage("@everyone " + message).queue();
                    } catch (NullPointerException ignored) {
                    }
                });
            }
        } else
            messageParams.getTextChannel().sendMessage("Only the bot owner can use this commad").queue();
    }
}
