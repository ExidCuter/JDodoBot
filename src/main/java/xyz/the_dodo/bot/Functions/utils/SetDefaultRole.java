package xyz.the_dodo.bot.Functions.utils;

import net.dv8tion.jda.core.entities.Role;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.DefaultRoleUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

import java.util.List;

public class SetDefaultRole extends IFunction {
    public SetDefaultRole(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Server server;
        List<Role> roles;
        DefaultRole defaultRole;

        roles = p_messageParams.getMessage().getMentionedRoles();
        if (AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            if (roles.size() == 1) {
                defaultRole = new DefaultRole();
                server = ServerUtils.m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

                defaultRole.setDiscordId(roles.get(0).getId());
                defaultRole.setServer(server);

                DefaultRoleUtils.saveDefaultRole(defaultRole);

                p_messageParams.getTextChannel().sendMessage("New default role was set: " + roles.get(0).getAsMention()).queue();
            } else
                p_messageParams.getTextChannel().sendMessage("You need to mention just one role!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can set or change the default role of the guild!").queue();
    }
}
