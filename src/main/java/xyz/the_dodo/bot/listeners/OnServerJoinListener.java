package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
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
            server = ServerUtils.m_serverService.findByDiscordId(guild.getId());

            if (DefaultRoleUtils.defaultRoleExists(server)) {
                defaultRole = DefaultRoleUtils.getDefaultRoleOfServer(server);

                role = guild.getRoleById(defaultRole.getDiscordId());

                guild.getController().addRolesToMember(member, role).queue();
            }
        }

        if (RulesUtils.rulesExist(guild)) {
            rules = RulesUtils.findByGuild(guild);

            member.getUser().openPrivateChannel().queue(p_privateChannel -> p_privateChannel.sendMessage(rules.getRules()).queue());
        }
    }
}
