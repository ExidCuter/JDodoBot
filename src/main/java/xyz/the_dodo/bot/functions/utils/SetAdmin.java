package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.database.types.Admin;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.User;

@BotService(command = "setAdmin", description = "Sets admin of the guild", usage = "setAdmin <USER MENTION>", category = CommandCategoryEnum.UTILS)
public class SetAdmin extends IFunction {
    public SetAdmin(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        User user;
        Admin admin;
        Server server;

        if (messageParams.getMessage().getMentionedMembers().size() > 0) {
            if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
                for (Member member : messageParams.getMessage().getMentionedMembers()) {
                    if (!UserUtils.userExists(member.getUser()))
                        UserUtils.createDodoUser(member.getUser());

                    user = UserUtils.userService.findByDiscordId(member.getUser().getId());

                    if (!ServerUtils.serverExist(messageParams.getGuild()))
                        ServerUtils.createServer(messageParams.getGuild());

                    server = ServerUtils.serverService.findByDiscordId(messageParams.getGuild().getId());

                    admin = new Admin();

                    admin.setServer(server);
                    admin.setUser(user);

                    admin = AdminUtils.adminService.save(admin);

                    if (admin != null)
                        messageParams.getTextChannel().sendMessage("User " + member.getAsMention() + " is now ADMIN").queue();
                    else
                        messageParams.getTextChannel().sendMessage("An error occurred!").queue();
                }
            } else
                messageParams.getTextChannel().sendMessage("Only admins or the owner of the Guild can add admins!").queue();
        } else
            messageParams.getTextChannel().sendMessage("You need to mention users that you want to add as admins!").queue();
    }
}
