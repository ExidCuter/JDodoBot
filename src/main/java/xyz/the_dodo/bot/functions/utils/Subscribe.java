package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.bot.utils.SubsUtils;

public class Subscribe extends IFunction {
    public Subscribe(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        int timerMulti;
        String command;

        if (p_messageParams.getMessage().getMember().isOwner() || AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            try {
                timerMulti = Integer.parseUnsignedInt(p_messageParams.getParameters()[0]);

                if (timerMulti < 1)
                    throw new Exception("Invalid number");

                command = StringUtils.glueStringsBackTogether(p_messageParams.getParameters(), " ", 1);

                SubsUtils.addSubscriptionToGuild(command, timerMulti, p_messageParams.getGuild(), p_messageParams.getTextChannel());

                p_messageParams.getTextChannel().sendMessage("Command `" + command + "` will be triggered every " + timerMulti * 10 + " minutes.").queue();
            } catch (Exception e) {
                p_messageParams.getTextChannel().sendMessage("`" + p_messageParams.getParameters()[0] + "` is not a valid number").queue();
            }

        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
    }
}
