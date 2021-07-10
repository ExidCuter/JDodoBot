package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.api.entities.Member;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.BannedUtils;

import java.util.List;

@BotService(command = "unbanUser", description = "Unbans an user or multiple users", usage = "unbanUser <MENTION USERS TO BAN>", category = CommandCategoryEnum.UTILS)
public class UnbanUser extends IFunction {
    public UnbanUser(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        List<Member> mentionedUsers;

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            mentionedUsers = messageParams.getMessage().getMentionedMembers();

            if (mentionedUsers.size() > 0) {
                mentionedUsers.forEach(member -> BannedUtils.unbanUserOnServer(member.getUser(), messageParams.getGuild()));
            } else {
                messageParams.getTextChannel().sendMessage("You need to mention users!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("Only admins can ban people!").queue();
        }

        return this;
    }
}
