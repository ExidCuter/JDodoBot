package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.bot.utils.SubsUtils;
import xyz.the_dodo.database.types.Subscription;

import java.util.List;

@BotService(command = "getSubs", description = "Displays all subscriptions of your guild.", usage = "getSubs", category = CommandCategoryEnum.UTILS)
public class GetSubscriptions extends IFunction {
    public GetSubscriptions(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        StringBuilder stringBuilder;
        List<Subscription> subscriptions;

        stringBuilder = new StringBuilder();
        subscriptions = SubsUtils.getSubscriptionsForGuild(messageParams.getGuild());

        if (subscriptions.size() > 0) {
            stringBuilder.append("You are subscribed to:\n");

            subscriptions.forEach(subscription ->
                    stringBuilder.append("\tid:`" + subscription.getId() + "` `" + subscription.getCommand() + "` on " + subscription.getTimer() * 10 + " minutes\n"));

            StringUtils.splitIntoMessages(stringBuilder.toString(), '\n').forEach(message -> messageParams.getTextChannel().sendMessage(message).queue());
        } else {
            messageParams.getTextChannel().sendMessage("Your guild has no subscriptions!").queue();
        }

        return this;
    }
}
