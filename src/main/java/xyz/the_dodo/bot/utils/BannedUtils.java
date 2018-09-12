package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.REST.service.BannedServiceImpl;
import xyz.the_dodo.database.interfaces.services.IBannedService;
import xyz.the_dodo.database.types.BannedUser;
import xyz.the_dodo.database.types.Server;

public class BannedUtils {
    public static IBannedService m_bannedService = BeanUtils.getBean(BannedServiceImpl.class);

    public static boolean isUserBannedOnServer(User p_user, Guild p_guild) {
        for (BannedUser p_bannedUser : m_bannedService.findByServerDiscordId(p_guild.getId())) {
            if (p_bannedUser.getUser().getDiscordId().equals(p_user.getId()))
                return true;
        }

        return false;
    }

    public static void banUserOnServer(User p_user, Guild p_guild) {
        Server server;
        BannedUser bannedUser;
        xyz.the_dodo.database.types.User user;

        user = UserUtils.m_userService.findByDiscordId(p_user.getId());
        server = ServerUtils.m_serverService.findByDiscordId(p_guild.getId());

        if (server != null && user != null) {
            if (!isUserBannedOnServer(p_user, p_guild)) {
                bannedUser = new BannedUser();

                bannedUser.setServer(server);
                bannedUser.setUser(user);

                m_bannedService.save(bannedUser);
            }
        }
    }

    public static void unbanUserOnServer(User p_user, Guild p_guild) {
        Server server;
        BannedUser bannedUser;
        xyz.the_dodo.database.types.User user;

        user = UserUtils.m_userService.findByDiscordId(p_user.getId());
        server = ServerUtils.m_serverService.findByDiscordId(p_guild.getId());

        if (server != null && user != null) {
            if (isUserBannedOnServer(p_user, p_guild)) {
                bannedUser = m_bannedService.findByUserAndServerDiscordId(p_user.getId(), p_guild.getId());

                m_bannedService.delete(bannedUser);
            }
        }
    }

}
