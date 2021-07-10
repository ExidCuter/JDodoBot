package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.api.entities.Member;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.database.types.Admin;

import java.util.ArrayList;
import java.util.List;

@BotService(command = "getAdmins", description = "Returns the list of admins", usage = "admins", category = CommandCategoryEnum.UTILS)
public class GetAdmins extends IFunction {
    public GetAdmins(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        List<Admin> admins;
        StringBuilder adminsOfGuild;
        List<Member> members, adminMembers;

        adminMembers = new ArrayList<>();
        adminsOfGuild = new StringBuilder();
        members = messageParams.getGuild().getMembers();
        admins = AdminUtils.adminService.getAdminsByServerId(messageParams.getGuild().getId());

        admins.forEach(admin -> {
            members.forEach(member -> {
                if (member.getUser().getId().equals(admin.getUser().getDiscordId())) {
                    adminMembers.add(member);
                }
            });
        });

        if (adminMembers.size() > 0) {
            adminsOfGuild.append("Admins of ").append(messageParams.getGuild().getName()).append(" are:\n");

            adminMembers.forEach(member -> adminsOfGuild.append(member.getAsMention()).append("\n"));

            messageParams.getTextChannel().sendMessage(adminsOfGuild.toString()).queue();
        } else {
            messageParams.getTextChannel().sendMessage("This guild has no admins. TOTAL ANARCHY!!!").queue();
        }

        return this;
    }
}
