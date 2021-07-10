package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import xyz.the_dodo.REST.service.UserServiceImpl;
import xyz.the_dodo.database.interfaces.services.IUserService;

public class UserUtils {
    public static IUserService userService = BeanUtils.getBean(UserServiceImpl.class);

    public static boolean userExists(@NotNull User discordUser) {
        xyz.the_dodo.database.types.User user;

        user = userService.findByDiscordId(discordUser.getId());

        return user != null;
    }

    public static void createDodoUser(@NotNull User discordUser) {
        xyz.the_dodo.database.types.User user;

        user = new xyz.the_dodo.database.types.User();

        user.setDiscordId(discordUser.getId());

        userService.save(user);
    }
}
