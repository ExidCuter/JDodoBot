package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.RulesUtils;

@BotService(command = "deleteRules", description = "Removes the guild rules!", usage = "deleteRules", category = CommandCategoryEnum.UTILS)
public class DeleteRules extends IFunction {
    public DeleteRules(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            if (RulesUtils.rulesExist(messageParams.getGuild())) {
                RulesUtils.deleteRules(messageParams.getGuild());

                messageParams.getTextChannel().sendMessage("Rules deleted!").queue();
            } else {
                messageParams.getTextChannel().sendMessage("Your guild has no rules total anarchy!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("Only admins can change the Guild Rules!").queue();
        }

        return this;
    }
}
