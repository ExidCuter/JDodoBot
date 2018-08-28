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

public class SetAdmin extends IFunction
{
	public SetAdmin(String command, String description, String usage)
	{
		super(command, description, usage);
	}

	@Override
	public void trigger(MessageParams p_messageParams)
	{
		User user;
		Admin admin;
		Server server;

		if(p_messageParams.getMessage().getMentionedMembers().size() > 0) {
			if(p_messageParams.getGuild().getOwner().getUser().getId().equals(p_messageParams.getUser().getId()) ||
					AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
				for(Member member : p_messageParams.getMessage().getMentionedMembers()){
					if(!UserUtils.userExists(member.getUser()))
						UserUtils.createDodoUser(member.getUser());

					user = UserUtils.m_userService.findByDiscordId(member.getUser().getId());

					if(!ServerUtils.serverExist(p_messageParams.getGuild()))
						ServerUtils.createServer(p_messageParams.getGuild());

					server = ServerUtils.m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

					admin = new Admin();

					admin.setServer(server);
					admin.setUser(user);

					admin = AdminUtils.m_adminService.save(admin);

					if(admin != null)
						p_messageParams.getTextChannel().sendMessage("User " + p_messageParams.getUser().getAsMention() + " is now ADMIN").queue();
					else
						p_messageParams.getTextChannel().sendMessage("An error occurred!").queue();
				}
			} else
				p_messageParams.getTextChannel().sendMessage("Only admins or the owner of the Guild can add admins!").queue();
		} else
			p_messageParams.getTextChannel().sendMessage("You need to mention users that you want to add as admins!").queue();
	}
}
