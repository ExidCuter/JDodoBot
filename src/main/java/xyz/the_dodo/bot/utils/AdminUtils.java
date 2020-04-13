package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.REST.service.AdminServiceImpl;
import xyz.the_dodo.config.BotConfig;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

public class AdminUtils {
    public static AdminServiceImpl adminService = BeanUtils.getBean(AdminServiceImpl.class);
    public static BotConfig config = BeanUtils.getBean(BotConfig.class);

    public static boolean isAdminOfGuild(User user, Guild guild) {
        List<Admin> admins;

        if (guild.getOwner().getUser().getId().equals(user.getId())) {
            return true;
        }

        admins = adminService.getAdminsByServerId(guild.getId());

        if (user.getId().equals(guild.getOwner().getUser().getId())) {
            return true;
        }

        for (Admin admin : admins) {
            if (admin.getUser().getDiscordId().equals(user.getId())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isUserBotOwner(User user) {
        return user.getId().equals(config.getBotOwner());
    }
}
