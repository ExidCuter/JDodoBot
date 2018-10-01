package xyz.the_dodo.bot.functions.admin;

import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;

public class LeaveGuild extends IFunction {
    public LeaveGuild(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.ADMIN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (AdminUtils.isUserBotOwner(p_messageParams.getUser())) {
            for (String s : p_messageParams.getParameters()) {
                DodoBot.leaveGuild(s);
            }
        }
    }
}
