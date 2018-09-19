package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.DeletedMessageUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.DeletedMessage;

import java.util.List;

public class GetDeletedMessages extends IFunction {
    public GetDeletedMessages(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        //TODO: deleted files
        int maxMessages;
        StringBuilder stringBuilder;
        List<DeletedMessage> deletedMessages;

        maxMessages = 5;
        stringBuilder = new StringBuilder();

        if (p_messageParams.getMessage().getMember().isOwner() || AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            if (p_messageParams.getMessage().getMentionedMembers().size() > 0) {
                if (p_messageParams.getParameters().length > p_messageParams.getMessage().getMentionedMembers().size()) {
                    try {
                        maxMessages = Integer.parseInt(p_messageParams.getParameters()[p_messageParams.getMessage().getMentionedMembers().size() + 1]);
                    } catch (Exception e) {
                        maxMessages = 5;
                    }
                }
            } else if (p_messageParams.getParameters().length > 0) {
                try {
                    maxMessages = Integer.parseInt(p_messageParams.getParameters()[0]);
                } catch (Exception e) {
                    maxMessages = 5;
                }

            }

            if (p_messageParams.getMessage().getMentionedMembers().size() > 0) {
                for (User user : p_messageParams.getMessage().getMentionedUsers()) {
                    deletedMessages = DeletedMessageUtils.getDeletedInGuild(user, p_messageParams.getGuild());
                    deletedMessages = deletedMessages.subList(Math.max(deletedMessages.size() - maxMessages, 0), deletedMessages.size());

                    if (deletedMessages.size() > 0) {
                        stringBuilder.append("Last ")
                                .append(deletedMessages.size())
                                .append(" deleted messages of ")
                                .append(user.getAsMention())
                                .append(" are:\n");

                        deletedMessages.forEach(p_deletedMessage -> stringBuilder.append("\t`").append(p_deletedMessage.getMessage()).append("`\n"));
                    } else
                        stringBuilder.append("No deleted messages!");
                }
            } else {
                deletedMessages = DeletedMessageUtils.m_deletedMessageService.findAllByServerDiscordId(p_messageParams.getGuild().getId());

                deletedMessages = deletedMessages.subList(Math.max(deletedMessages.size() - maxMessages, 0), deletedMessages.size());

                if (deletedMessages.size() > 0) {
                    stringBuilder.append("Last ")
                            .append(deletedMessages.size())
                            .append(" deleted messages of ")
                            .append(p_messageParams.getGuild().getName())
                            .append(" are:\n");

                    deletedMessages.forEach(p_deletedMessage ->
                            stringBuilder.append("\t`").append(p_deletedMessage.getMessage())
                                    .append("` by ")
                                    .append(p_messageParams.getGuild().getMemberById(p_deletedMessage.getUser().getDiscordId()).getAsMention())
                                    .append("\n"));
                } else
                    stringBuilder.append("No deleted messages!");
            }
            StringUtils.splitIntoMessages(stringBuilder.toString(), '\n')
                    .forEach(s -> p_messageParams.getTextChannel().sendMessage(s).queue());
        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
    }
}
