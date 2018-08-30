package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.Server;

public class OnAddedToServerListener extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Server server;

        if (!ServerUtils.serverExist(event.getGuild())) {
            server = new Server();

            server.setDiscordId(event.getGuild().getId());

            ServerUtils.m_serverService.save(server);

            try {
                event.getGuild().getDefaultChannel().sendMessage("Thank you for adding DodoBot! For the list of commands type `!help`").queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
