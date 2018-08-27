package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.the_dodo.database.interfaces.services.IServerService;
import xyz.the_dodo.database.types.Server;

public class ServerUtils
{
	@Autowired
	private static IServerService m_serverService;

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
