package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.ServerServiceImpl;
import xyz.the_dodo.database.interfaces.services.IServerService;
import xyz.the_dodo.database.types.Server;

public class ServerUtils {
    public static IServerService m_serverService = BeanUtils.getBean(ServerServiceImpl.class);

    public static boolean serverExist(Guild p_guild) {
        Server server;

        server = m_serverService.findByDiscordId(p_guild.getId());

        return server != null;
    }

    public static void createServer(Guild p_guild) {
        Server server;

        server = new Server();

        server.setDiscordId(p_guild.getId());

        m_serverService.save(server);
    }
}
