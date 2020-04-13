package xyz.the_dodo.bot.functions.admin;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;

@BotService(command = "leaveGuild", category = CommandCategoryEnum.ADMIN)
public class LeaveGuild extends IFunction {
    public LeaveGuild(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        if (AdminUtils.isUserBotOwner(messageParams.getUser())) {
            for (String s : messageParams.getParameters()) {
                config.leaveGuild(s);
            }
        }
    }
}
