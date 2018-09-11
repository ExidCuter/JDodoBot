package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.DefaultRoleUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

public class GetDefaultRole extends IFunction {
    public GetDefaultRole(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Server server;
        DefaultRole defaultRole;

        if (ServerUtils.serverExist(p_messageParams.getGuild())) {
            server = ServerUtils.m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

            defaultRole = DefaultRoleUtils.getDefaultRoleOfServer(server);

            if (defaultRole != null) {
                p_messageParams.getTextChannel().sendMessage("Default role of this guild is " +
                        p_messageParams.getGuild().getRoleById(defaultRole.getDiscordId()).getAsMention()).queue();
                return;
            }
        }

        p_messageParams.getTextChannel().sendMessage("Default role is not set!").queue();
    }
}
