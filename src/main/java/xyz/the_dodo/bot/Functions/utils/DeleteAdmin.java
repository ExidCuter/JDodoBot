package xyz.the_dodo.bot.Functions.utils;

import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.database.types.Admin;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.User;

import java.util.List;

public class DeleteAdmin extends IFunction
{
	public DeleteAdmin(String command, String description, String usage)
	{
		super(command, description, usage);
	}

	@Override
	public void trigger(MessageParams p_messageParams)
	{
		Server server;
		List<Admin> admins;

		if(p_messageParams.getMessage().getMentionedMembers().size() > 0) {
			if(p_messageParams.getGuild().getOwner().getUser().getId().equals(p_messageParams.getUser().getId()) ||
					AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
				for(Member member : p_messageParams.getMessage().getMentionedMembers()){
					if(UserUtils.userExists(member.getUser())) {
						if(ServerUtils.serverExist(p_messageParams.getGuild())) {
							server = ServerUtils.m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

							admins = AdminUtils.m_adminService.getAdminsByServerId(server.getDiscordId());

							admins.forEach(p_admin -> {
								if(p_admin.getUser().getDiscordId().equals(member.getUser().getId())) {
									AdminUtils.m_adminService.delete(p_admin);
									p_messageParams.getTextChannel().sendMessage("User " + p_messageParams.getUser().getAsMention() + " is ADMIN no more").queue();
								} else
									p_messageParams.getTextChannel().sendMessage("An error occurred!").queue();
							});

							return;
						}
					}
					p_messageParams.getTextChannel().sendMessage("An error occurred!").queue();
				}
			} else
				p_messageParams.getTextChannel().sendMessage("Only admins or the owner of the Guild can remove admins!").queue();
		} else
			p_messageParams.getTextChannel().sendMessage("You need to mention users that you want to remove from admins!").queue();
	}
}
