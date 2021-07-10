package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
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
    public IFunction prepare(MessageParams messageParams) {
        Server server;
        DefaultRole defaultRole;

        if (ServerUtils.serverExist(messageParams.getGuild())) {
            server = ServerUtils.serverService.findByDiscordId(messageParams.getGuild().getId());

            defaultRole = DefaultRoleUtils.getDefaultRoleOfServer(server);

            if (defaultRole != null) {
                messageParams.getTextChannel().sendMessage("Default role of this guild is " +
                        messageParams.getGuild().getRoleById(defaultRole.getDiscordId()).getAsMention()).queue();

                return this;
            }
        }

        messageParams.getTextChannel().sendMessage("Default role is not set!").queue();

        return this;
    }
}
