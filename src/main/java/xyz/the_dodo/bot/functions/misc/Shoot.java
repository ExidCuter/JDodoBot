package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.ImageUtils;

import java.util.List;

@BotService(command = "shoot", description = "Shoots ya", usage = "shoot/shoot <#USER MENTION>")
public class Shoot extends IFunction {
    public Shoot(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        List<User> users;

        users = messageParams.getMessage().getMentionedUsers();
        if (users.isEmpty())
            messageParams.getTextChannel().sendFile(ImageUtils.generateShoot(messageParams.getUser().getAvatarUrl()).toByteArray(), "shoot.gif").queue();
        else
            users.forEach(p_user -> messageParams.getTextChannel().sendFile(ImageUtils.generateShoot(p_user.getAvatarUrl()).toByteArray(), "shoot.gif").queue());
    }
}
