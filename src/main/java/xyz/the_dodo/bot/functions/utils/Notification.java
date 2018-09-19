package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

public class Notification extends IFunction {
    public Notification(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        String message;

        if (p_messageParams.getUser().getId().equals(DodoBot.botOwner)) {
            if (p_messageParams.getParameters().length > 0) {
                message = StringUtils.glueStringsBackTogether(p_messageParams.getParameters(), " ", 0);

                DodoBot.getGuilds().forEach(p_guild -> {
                    try {
                        p_guild.getDefaultChannel().sendMessage("@everyone " + message).queue();
                    } catch (NullPointerException ignored) { }
                });
            }
        } else
            p_messageParams.getTextChannel().sendMessage("Only the bot owner can use this commad").queue();
    }
}
