package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.RulesUtils;

public class DeleteRules extends IFunction {
    public DeleteRules(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            if (RulesUtils.rulesExist(p_messageParams.getGuild())) {
                RulesUtils.deleteRules(p_messageParams.getGuild());

                p_messageParams.getTextChannel().sendMessage("Rules deleted!").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("Your guild has no rules total anarchy!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can change the Guild Rules!").queue();
    }
}
