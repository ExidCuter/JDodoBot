package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.REST.service.AdminServiceImpl;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

public class AdminUtils {
    public static AdminServiceImpl m_adminService = BeanUtils.getBean(AdminServiceImpl.class);

    public static boolean isAdminOfGuild(User user, Guild guild) {
        List<Admin> admins;

        if (guild.getOwner().getUser().getId().equals(user.getId()))
            return true;

        admins = m_adminService.getAdminsByServerId(guild.getId());

        if (user.getId().equals(guild.getOwner().getUser().getId()))
            return true;

        for (Admin admin : admins) {
            if (admin.getUser().getDiscordId().equals(user.getId()))
                return true;
        }

        return false;
    }

    public static boolean isUserBotOwner(User p_user) {
        return p_user.getId().equals(DodoBot.botOwner);
    }
}
