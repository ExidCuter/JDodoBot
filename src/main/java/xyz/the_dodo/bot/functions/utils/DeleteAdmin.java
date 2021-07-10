package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.api.entities.Member;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.database.types.Admin;
import xyz.the_dodo.database.types.Server;

import java.util.List;

@BotService(command = "delAdmin", description = "Removes user from the admin list", usage = "delAdmin <MENTION USERS>", category = CommandCategoryEnum.UTILS)
public class DeleteAdmin extends IFunction {
    public DeleteAdmin(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Server server;
        List<Admin> admins;

        if (messageParams.getMessage().getMentionedMembers().size() > 0) {
            if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
                for (Member member : messageParams.getMessage().getMentionedMembers()) {
                    if (UserUtils.userExists(member.getUser())) {
                        if (ServerUtils.serverExist(messageParams.getGuild())) {
                            server = ServerUtils.serverService.findByDiscordId(messageParams.getGuild().getId());

                            admins = AdminUtils.adminService.getAdminsByServerId(server.getDiscordId());

                            admins.forEach(admin -> {
                                if (admin.getUser().getDiscordId().equals(member.getUser().getId())) {
                                    AdminUtils.adminService.delete(admin);
                                    messageParams.getTextChannel().sendMessage("User " + messageParams.getUser().getAsMention() + " is ADMIN no more").queue();
                                } else {
                                    messageParams.getTextChannel().sendMessage("An error occurred!").queue();
                                }
                            });

                            return null;
                        }
                    }
                    messageParams.getTextChannel().sendMessage("An error occurred!").queue();
                }
            } else {
                messageParams.getTextChannel().sendMessage("Only admins or the owner of the Guild can remove admins!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("You need to mention users that you want to remove from admins!").queue();
        }

        return this;
    }
}
