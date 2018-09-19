package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.BannedUtils;

import java.util.List;

public class BanUser extends IFunction {
    public BanUser(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<Member> mentionedUsers;
        if (p_messageParams.getMessage().getMember().isOwner() || AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            mentionedUsers = p_messageParams.getMessage().getMentionedMembers();

            if (mentionedUsers.size() > 0) {
                mentionedUsers.forEach(p_member -> BannedUtils.banUserOnServer(p_member.getUser(), p_messageParams.getGuild()));
            } else
                p_messageParams.getTextChannel().sendMessage("You need to mention users!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can ban people!").queue();
    }
}
