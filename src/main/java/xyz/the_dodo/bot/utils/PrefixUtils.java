package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.PrefixServiceImpl;
import xyz.the_dodo.database.interfaces.services.IPrefixService;
import xyz.the_dodo.database.types.Prefix;
import xyz.the_dodo.database.types.Server;

public class PrefixUtils {
    public static IPrefixService m_prefixService = BeanUtils.getBean(PrefixServiceImpl.class);

    public static boolean guildHasCustomPrefix(Guild p_guild) {
        Prefix prefix;

        prefix = m_prefixService.getByServerDiscordId(p_guild.getId());

        return prefix != null;
    }

    public static void setCustomPrefixForGuild(Guild p_guild, String p_prefix) {
        Server server;
        Prefix prefix;

        if (!ServerUtils.serverExist(p_guild))
            ServerUtils.createServer(p_guild);

        if (guildHasCustomPrefix(p_guild)) {
            prefix = m_prefixService.getByServerDiscordId(p_guild.getId());
            prefix.setPrefix(p_prefix);
        } else {
            prefix = new Prefix();
            server = ServerUtils.m_serverService.findByDiscordId(p_guild.getId());

            prefix.setServer(server);
            prefix.setPrefix(p_prefix);
        }

        m_prefixService.save(prefix);
    }
}
