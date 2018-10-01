package xyz.the_dodo.bot.functions.admin;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.listeners.StatsListener;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.StringUtils;

public class GetStatistics extends IFunction {
    public GetStatistics(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.ADMIN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        StringBuilder stringBuilder;

        if (AdminUtils.isUserBotOwner(p_messageParams.getUser())) {
            stringBuilder = new StringBuilder();

            StatsListener.userInteractions.forEach((p_key, p_value) -> stringBuilder.append("`" + p_key + "`: " + p_value));

            StringUtils.splitIntoMessages(stringBuilder.toString(), '\n').forEach(p_message -> p_messageParams.getTextChannel().sendMessage(p_message).queue());
        }
    }
}
