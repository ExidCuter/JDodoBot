package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.PrefixUtils;

public class SetCustomPrefix extends IFunction {
    public SetCustomPrefix(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            if (p_messageParams.getParameters().length > 0) {
                PrefixUtils.setCustomPrefixForGuild(p_messageParams.getGuild(), p_messageParams.getParameters()[0]);

                p_messageParams.getTextChannel().sendMessage("Prefix was set to `" + p_messageParams.getParameters()[0] + "`!").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("No prefix specified!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can set the prefix!").queue();
    }
}
