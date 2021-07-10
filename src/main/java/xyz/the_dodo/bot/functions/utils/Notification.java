package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

@BotService(command = "notifyAll", description = "Sends a notification to all servers! Only bot owner can use this commad!", usage = "notifyAll <MESSAGE>", category = CommandCategoryEnum.UTILS)
public class Notification extends IFunction {
    public Notification(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        String message;

        if (messageParams.getUser().getId().equals(config.getBotOwner())) {
            if (messageParams.getParameters().length > 0) {
                message = StringUtils.glueStringsBackTogether(messageParams.getParameters(), " ", 0);

                config.getGuilds().forEach(guild -> {
                    try {
                        guild.getDefaultChannel().sendMessage("@everyone " + message).queue();
                    } catch (NullPointerException ignored) {
                    }
                });
            }
        } else {
            messageParams.getTextChannel().sendMessage("Only the bot owner can use this commad").queue();
        }

        return this;
    }
}
