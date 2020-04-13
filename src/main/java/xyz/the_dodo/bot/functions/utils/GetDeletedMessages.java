package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.DeletedMessageUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.DeletedMessage;

import java.util.List;

@BotService(command = "getDeleted", description = "Gets deleted messages from this guild or a specific user of this guild!", usage = "getDeleted <#MENTION> <#AMOUNT>", category = CommandCategoryEnum.UTILS)
public class GetDeletedMessages extends IFunction {
    public GetDeletedMessages(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        //TODO: deleted files
        int maxMessages;
        StringBuilder stringBuilder;
        List<DeletedMessage> deletedMessages;

        maxMessages = 5;
        stringBuilder = new StringBuilder();

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            if (messageParams.getMessage().getMentionedMembers().size() > 0) {
                if (messageParams.getParameters().length > messageParams.getMessage().getMentionedMembers().size()) {
                    try {
                        maxMessages = Integer.parseInt(messageParams.getParameters()[messageParams.getMessage().getMentionedMembers().size() + 1]);
                    } catch (Exception e) {
                        maxMessages = 5;
                    }
                }
            } else if (messageParams.getParameters().length > 0) {
                try {
                    maxMessages = Integer.parseInt(messageParams.getParameters()[0]);
                } catch (Exception e) {
                    maxMessages = 5;
                }
            }

            if (messageParams.getMessage().getMentionedMembers().size() > 0) {
                for (User user : messageParams.getMessage().getMentionedUsers()) {
                    deletedMessages = DeletedMessageUtils.getDeletedInGuild(user, messageParams.getGuild());
                    deletedMessages = deletedMessages.subList(Math.max(deletedMessages.size() - maxMessages, 0), deletedMessages.size());

                    if (deletedMessages.size() > 0) {
                        stringBuilder.append("Last ")
                                .append(deletedMessages.size())
                                .append(" deleted messages of ")
                                .append(user.getAsMention())
                                .append(" are:\n");

                        deletedMessages.forEach(deletedMessage -> stringBuilder.append("\t`").append(deletedMessage.getMessage()).append("`\n"));
                    } else
                        stringBuilder.append("No deleted messages!");
                }
            } else {
                deletedMessages = DeletedMessageUtils.deletedMessageService.findAllByServerDiscordId(messageParams.getGuild().getId());

                deletedMessages = deletedMessages.subList(Math.max(deletedMessages.size() - maxMessages, 0), deletedMessages.size());

                if (deletedMessages.size() > 0) {
                    stringBuilder.append("Last ")
                            .append(deletedMessages.size())
                            .append(" deleted messages of ")
                            .append(messageParams.getGuild().getName())
                            .append(" are:\n");

                    deletedMessages.forEach(deletedMessage ->
                            stringBuilder.append("\t`").append(deletedMessage.getMessage())
                                    .append("` by ")
                                    .append(messageParams.getGuild().getMemberById(deletedMessage.getUser().getDiscordId()).getAsMention())
                                    .append("\n"));
                } else
                    stringBuilder.append("No deleted messages!");
            }
            StringUtils.splitIntoMessages(stringBuilder.toString(), '\n')
                    .forEach(s -> messageParams.getTextChannel().sendMessage(s).queue());
        } else
            messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
    }
}
