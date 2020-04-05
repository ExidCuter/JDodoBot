package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.DefaultRoleUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

@BotService(command = "getDefaultRole", description = "Gets the name of default role", usage = "getDefaultRole", category = CommandCategoryEnum.UTILS)
public class GetDefaultRole extends IFunction {
    public GetDefaultRole(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Server server;
        DefaultRole defaultRole;

        if (ServerUtils.serverExist(messageParams.getGuild())) {
            server = ServerUtils.m_serverService.findByDiscordId(messageParams.getGuild().getId());

            defaultRole = DefaultRoleUtils.getDefaultRoleOfServer(server);

            if (defaultRole != null) {
                messageParams.getTextChannel().sendMessage("Default role of this guild is " +
                        messageParams.getGuild().getRoleById(defaultRole.getDiscordId()).getAsMention()).queue();
                return;
            }
        }

        messageParams.getTextChannel().sendMessage("Default role is not set!").queue();
    }
}
