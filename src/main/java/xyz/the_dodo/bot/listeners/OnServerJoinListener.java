package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import xyz.the_dodo.bot.utils.DefaultRoleUtils;
import xyz.the_dodo.bot.utils.RulesUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Rules;
import xyz.the_dodo.database.types.Server;

public class OnServerJoinListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Role role;
        Guild guild;
        Rules rules;
        Member member;
        Server server;
        DefaultRole defaultRole;

        guild = event.getGuild();
        member = event.getMember();

        if (ServerUtils.serverExist(guild)) {
            server = ServerUtils.serverService.findByDiscordId(guild.getId());

            if (DefaultRoleUtils.defaultRoleExists(server)) {
                defaultRole = DefaultRoleUtils.getDefaultRoleOfServer(server);

                role = guild.getRoleById(defaultRole.getDiscordId());

                if (role != null) {
                    guild.addRoleToMember(member, role).queue();
                }
            }
        }

        if (RulesUtils.rulesExist(guild)) {
            rules = RulesUtils.findByGuild(guild);

            member.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(rules.getRules()).queue());
        }
    }
}
