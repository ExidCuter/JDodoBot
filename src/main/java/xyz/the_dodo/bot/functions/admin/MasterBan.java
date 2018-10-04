package xyz.the_dodo.bot.functions.admin;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.UserUtils;

import java.util.List;

public class MasterBan extends IFunction {
    public MasterBan(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.ADMIN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<User> mentionedUsers;

        if (AdminUtils.isUserBotOwner(p_messageParams.getUser())) {
            mentionedUsers = p_messageParams.getMessage().getMentionedUsers();

            if (mentionedUsers.size() > 0) {
                mentionedUsers.forEach(p_user -> {
                    xyz.the_dodo.database.types.User user;

                    if (!UserUtils.userExists(p_user))
                        UserUtils.createDodoUser(p_user);

                    user = UserUtils.m_userService.findByDiscordId(p_user.getId());

                    user.setBanned(!user.isBanned());

                    UserUtils.m_userService.save(user);

                    if (user.isBanned())
                        p_messageParams.getTextChannel().sendMessage("Banned " + p_user.getAsMention() + " from using the bot!").queue();
                    else
                        p_messageParams.getTextChannel().sendMessage("UnBanned " + p_user.getAsMention() + " from using the bot!").queue();
                });
            } else
                p_messageParams.getTextChannel().sendMessage("Please mention a user!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("You are not the Bot Owner!").queue();
    }
}
