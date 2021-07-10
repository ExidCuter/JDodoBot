package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.api.entities.Role;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.DefaultRoleUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

import java.util.List;

@BotService(command = "setDefaultRole", description = "When a new user joins your guild they are set to this role!", usage = "setDefaultRole <ROLE MENTION>", category = CommandCategoryEnum.UTILS)
public class SetDefaultRole extends IFunction {
    public SetDefaultRole(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Server server;
        List<Role> roles;
        DefaultRole defaultRole;

        roles = messageParams.getMessage().getMentionedRoles();
        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            if (roles.size() == 1) {
                defaultRole = new DefaultRole();
                server = ServerUtils.serverService.findByDiscordId(messageParams.getGuild().getId());

                defaultRole.setDiscordId(roles.get(0).getId());
                defaultRole.setServer(server);

                DefaultRoleUtils.saveDefaultRole(defaultRole);

                messageParams.getTextChannel().sendMessage("New default role was set: " + roles.get(0).getAsMention()).queue();
            } else {
                messageParams.getTextChannel().sendMessage("You need to mention just one role!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("Only admins can set or change the default role of the guild!").queue();
        }

        return this;
    }
}
