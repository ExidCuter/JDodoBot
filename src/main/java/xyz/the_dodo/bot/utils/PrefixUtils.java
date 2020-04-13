package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.PrefixServiceImpl;
import xyz.the_dodo.database.interfaces.services.IPrefixService;
import xyz.the_dodo.database.types.Prefix;
import xyz.the_dodo.database.types.Server;

public class PrefixUtils {
    public static IPrefixService prefixService = BeanUtils.getBean(PrefixServiceImpl.class);

    public static boolean guildHasCustomPrefix(Guild guild) {
        Prefix prefix;

        prefix = prefixService.getByServerDiscordId(guild.getId());

        return prefix != null;
    }

    public static void setCustomPrefixForGuild(Guild guild, String newPrefix) {
        Server server;
        Prefix prefix;

        if (!ServerUtils.serverExist(guild)) {
            ServerUtils.createServer(guild);
        }

        if (guildHasCustomPrefix(guild)) {
            prefix = prefixService.getByServerDiscordId(guild.getId());
        } else {
            prefix = new Prefix();
            server = ServerUtils.serverService.findByDiscordId(guild.getId());

            prefix.setServer(server);
        }

        prefix.setPrefix(newPrefix);

        prefixService.save(prefix);
    }
}
