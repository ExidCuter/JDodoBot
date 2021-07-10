package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.PrefixUtils;

@BotService(command = "setPrefix", description = "Sets a custom prefix for your guild", usage = "setPrefix <PREFIX>", category = CommandCategoryEnum.UTILS)
public class SetCustomPrefix extends IFunction {
    public SetCustomPrefix(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            if (messageParams.getParameters().length > 0) {
                PrefixUtils.setCustomPrefixForGuild(messageParams.getGuild(), messageParams.getParameters()[0]);

                messageParams.getTextChannel().sendMessage("Prefix was set to `" + messageParams.getParameters()[0] + "`!").queue();
            } else {
                messageParams.getTextChannel().sendMessage("No prefix specified!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("Only admins can set the prefix!").queue();
        }

        return this;
    }
}
