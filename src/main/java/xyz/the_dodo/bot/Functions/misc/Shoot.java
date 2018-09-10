package xyz.the_dodo.bot.Functions.misc;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.ImageUtils;

import java.util.List;

public class Shoot extends IFunction {
    public Shoot(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<User> users;

        users = p_messageParams.getMessage().getMentionedUsers();
        if (users.isEmpty())
            p_messageParams.getTextChannel().sendFile(ImageUtils.generateShoot(p_messageParams.getUser().getAvatarUrl()).toByteArray(), "shoot.gif").queue();
        else
            users.forEach(p_user -> p_messageParams.getTextChannel().sendFile(ImageUtils.generateShoot(p_user.getAvatarUrl()).toByteArray(), "shoot.gif").queue());
    }
}
