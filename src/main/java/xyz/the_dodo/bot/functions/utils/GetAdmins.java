package xyz.the_dodo.bot.functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
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
    public void trigger(MessageParams messageParams) {
        List<Admin> admins;
        StringBuilder stringBuilder;
        List<Member> members, adminMembers;

        adminMembers = new ArrayList<>();
        stringBuilder = new StringBuilder();
        members = messageParams.getGuild().getMembers();
        admins = AdminUtils.m_adminService.getAdminsByServerId(messageParams.getGuild().getId());

        admins.forEach(p_admin -> {
            members.forEach(p_member -> {
                if (p_member.getUser().getId().equals(p_admin.getUser().getDiscordId()))
                    adminMembers.add(p_member);
            });
        });

        if (adminMembers.size() > 0) {
            stringBuilder.append("Admins of " + messageParams.getGuild().getName() + " are:\n");

            adminMembers.forEach(p_member -> stringBuilder.append(p_member.getAsMention() + "\n"));

            messageParams.getTextChannel().sendMessage(stringBuilder.toString()).queue();
        } else
            messageParams.getTextChannel().sendMessage("This guild has no admins. TOTAL ANARCHY!!!").queue();
    }
}
