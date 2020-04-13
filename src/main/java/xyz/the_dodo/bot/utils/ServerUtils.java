package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.ServerServiceImpl;
import xyz.the_dodo.database.interfaces.services.IServerService;
import xyz.the_dodo.database.types.Server;

public class ServerUtils {
    public static IServerService serverService = BeanUtils.getBean(ServerServiceImpl.class);

    public static boolean serverExist(Guild guild) {
        Server server;

        server = serverService.findByDiscordId(guild.getId());

        return server != null;
    }

    public static void createServer(Guild guild) {
        Server server;

        server = new Server();

        server.setDiscordId(guild.getId());
        server.setSaveDeleted(false);

        serverService.save(server);
    }
}
