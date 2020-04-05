package xyz.the_dodo.bot.functions.admin;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.UserUtils;

import java.util.List;

@BotService(command = "gameEndUser", category = CommandCategoryEnum.ADMIN)
public class MasterBan extends IFunction {
    public MasterBan(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        List<User> mentionedUsers;

        if (AdminUtils.isUserBotOwner(messageParams.getUser())) {
            mentionedUsers = messageParams.getMessage().getMentionedUsers();

            if (mentionedUsers.size() > 0) {
                mentionedUsers.forEach(p_user -> {
                    xyz.the_dodo.database.types.User user;

                    if (!UserUtils.userExists(p_user))
                        UserUtils.createDodoUser(p_user);

                    user = UserUtils.m_userService.findByDiscordId(p_user.getId());

                    user.setBanned(!user.isBanned());

                    UserUtils.m_userService.save(user);

                    if (user.isBanned())
                        messageParams.getTextChannel().sendMessage("Banned " + p_user.getAsMention() + " from using the bot!").queue();
                    else
                        messageParams.getTextChannel().sendMessage("UnBanned " + p_user.getAsMention() + " from using the bot!").queue();
                });
            } else
                messageParams.getTextChannel().sendMessage("Please mention a user!").queue();
        } else
            messageParams.getTextChannel().sendMessage("You are not the Bot Owner!").queue();
    }
}
