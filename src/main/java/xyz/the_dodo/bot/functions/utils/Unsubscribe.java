package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.SubsUtils;

public class Unsubscribe extends IFunction {
    public Unsubscribe(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        int id;

        if (AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            try {
                id = Integer.parseUnsignedInt(p_messageParams.getParameters()[0]);

                if (id < 1)
                    throw new Exception("Invalid number");

                SubsUtils.removeSubscriptionFromGuild(id, p_messageParams.getGuild());

                p_messageParams.getTextChannel().sendMessage("Unsubed!").queue();
            } catch (Exception e) {
                p_messageParams.getTextChannel().sendMessage("`" + p_messageParams.getParameters()[0] + "` is not a valid number").queue();
            }

        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
    }
}
