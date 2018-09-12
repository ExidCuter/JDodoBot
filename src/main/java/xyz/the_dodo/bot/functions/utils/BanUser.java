package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;

import java.util.List;

public class BanUser extends IFunction {
    public BanUser(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<Member> mentionedUsers;
        if (p_messageParams.getMessage().getMember().isOwner() || AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            mentionedUsers = p_messageParams.getMessage().getMentionedMembers();

            if (mentionedUsers.size() > 0) {

            }
        }
    }
}
