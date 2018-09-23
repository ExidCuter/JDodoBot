package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.bot.utils.SubsUtils;
import xyz.the_dodo.database.types.Subscription;

import java.util.List;

public class GetSubscriptions extends IFunction {
    public GetSubscriptions(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        StringBuilder stringBuilder;
        List<Subscription> subscriptions;

        stringBuilder = new StringBuilder();
        subscriptions = SubsUtils.getSubscriptionsForGuild(p_messageParams.getGuild());

        if (subscriptions.size() > 0) {
            stringBuilder.append("You are subscribed to:\n");

            subscriptions.forEach(subscription ->
                    stringBuilder.append("\tid:`" + subscription.getId() + "` `" + subscription.getCommand() + "` on "+ subscription.getTimer() * 10 + " minutes\n"));

            StringUtils.splitIntoMessages(stringBuilder.toString(), '\n').forEach(message -> p_messageParams.getTextChannel().sendMessage(message).queue());
        } else
            p_messageParams.getTextChannel().sendMessage("Your guild has no subscriptions!").queue();
    }
}
