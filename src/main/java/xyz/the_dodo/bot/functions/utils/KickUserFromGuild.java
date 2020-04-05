package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;

import java.util.List;

@BotService(command = "guild.kick", description = "Kicks user/users from guild!", usage = "guild.kick <MENTIONED USERS>", category = CommandCategoryEnum.UTILS)
public class KickUserFromGuild extends IFunction {
    public KickUserFromGuild(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        List<Member> members;
        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            members = messageParams.getMessage().getMentionedMembers();

            if (members.size() > 0) {
                members.forEach(p_member -> messageParams.getGuild().getController().kick(p_member).queue());
                messageParams.getMessage().addReaction("\u2705").queue();
            } else
                messageParams.getTextChannel().sendMessage("Please mention users to kick").queue();
        } else
            messageParams.getTextChannel().sendMessage("You are not the admin of this guild!").queue();
    }
}
