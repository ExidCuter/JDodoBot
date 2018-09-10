package xyz.the_dodo.bot.Functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.database.types.Admin;

import java.util.ArrayList;
import java.util.List;

public class GetAdmins extends IFunction {
    public GetAdmins(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<Admin> admins;
        StringBuilder stringBuilder;
        List<Member> members, adminMembers;

        adminMembers = new ArrayList<>();
        stringBuilder = new StringBuilder();
        members = p_messageParams.getGuild().getMembers();
        admins = AdminUtils.m_adminService.getAdminsByServerId(p_messageParams.getGuild().getId());

        admins.forEach(p_admin -> {
            members.forEach(p_member -> {
                if (p_member.getUser().getId().equals(p_admin.getUser().getDiscordId()))
                    adminMembers.add(p_member);
            });
        });

        if (adminMembers.size() > 0) {
            stringBuilder.append("Admins of " + p_messageParams.getGuild().getName() + " are:\n");

            adminMembers.forEach(p_member -> stringBuilder.append(p_member.getAsMention() + "\n"));

            p_messageParams.getTextChannel().sendMessage(stringBuilder.toString()).queue();
        } else
            p_messageParams.getTextChannel().sendMessage("This guild has no admins. TOTAL ANARCHY!!!").queue();
    }
}
