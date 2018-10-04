package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;

import java.util.List;

public class BanUserFromGuild extends IFunction {
    public BanUserFromGuild(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<Member> members;
        if(AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            members = p_messageParams.getMessage().getMentionedMembers();

            if (members.size() > 0) {
                members.forEach(p_member -> p_messageParams.getGuild().getController().ban(p_member, 10).queue());
                p_messageParams.getMessage().addReaction("\u2705").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("Please mention users to ban").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("You are not the admin of this guild!").queue();
    }
}
