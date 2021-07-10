package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.SubsUtils;

@BotService(command = "unsub", description = "Unsubscribes you from a command.", usage = "unsub <ID>", category = CommandCategoryEnum.UTILS)
public class Unsubscribe extends IFunction {
    public Unsubscribe(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        int id;

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            try {
                id = Integer.parseUnsignedInt(messageParams.getParameters()[0]);

                if (id < 1) {
                    throw new Exception("Invalid number");
                }

                SubsUtils.removeSubscriptionFromGuild(id, messageParams.getGuild());

                messageParams.getTextChannel().sendMessage("Unsubed!").queue();
            } catch (Exception e) {
                messageParams.getTextChannel().sendMessage("`" + messageParams.getParameters()[0] + "` is not a valid number").queue();
            }

        } else {
            messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
        }

        return this;
    }
}
