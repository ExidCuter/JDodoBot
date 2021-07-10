package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import xyz.the_dodo.REST.service.BannedServiceImpl;
import xyz.the_dodo.database.interfaces.services.IBannedService;
import xyz.the_dodo.database.types.BannedUser;
import xyz.the_dodo.database.types.Server;

import java.util.List;
import java.util.stream.Collectors;

public class BannedUtils {
    public static IBannedService bannedService = BeanUtils.getBean(BannedServiceImpl.class);

    public static boolean isUserBannedOnServer(User discordUser, Guild guild) {
        xyz.the_dodo.database.types.User user;
        List<BannedUser> bannedUsers;

        user = UserUtils.userService.findByDiscordId(discordUser.getId());
        bannedUsers = bannedService.findByServerDiscordId(guild.getId());

        if (user != null) {
            if (user.isBanned()) {
                return true;
            }
        } else {
            return false;
        }

        bannedUsers = bannedUsers.stream()
                .filter(bannedUser -> bannedUser.getUser().getDiscordId().equals(discordUser.getId()))
                .collect(Collectors.toList());

        return bannedUsers.size() > 0;
    }

    public static void banUserOnServer(User discordUser, Guild guild) {
        Server server;
        BannedUser bannedUser;
        xyz.the_dodo.database.types.User user;

        if (!UserUtils.userExists(discordUser))
            UserUtils.createDodoUser(discordUser);

        if (!ServerUtils.serverExist(guild))
            ServerUtils.createServer(guild);

        user = UserUtils.userService.findByDiscordId(discordUser.getId());
        server = ServerUtils.serverService.findByDiscordId(guild.getId());

        if (server != null && user != null) {
            if (!isUserBannedOnServer(discordUser, guild)) {
                bannedUser = new BannedUser();

                bannedUser.setServer(server);
                bannedUser.setUser(user);

                bannedService.save(bannedUser);
            }
        }
    }

    public static void unbanUserOnServer(User discordUser, Guild guild) {
        Server server;
        BannedUser bannedUser;
        xyz.the_dodo.database.types.User user;

        user = UserUtils.userService.findByDiscordId(discordUser.getId());
        server = ServerUtils.serverService.findByDiscordId(guild.getId());

        if (server != null && user != null) {
            if (isUserBannedOnServer(discordUser, guild)) {
                bannedUser = bannedService.findByUserAndServerDiscordId(discordUser.getId(), guild.getId());

                bannedService.delete(bannedUser);
            }
        }
    }

}
