package xyz.the_dodo.bot.Functions.utils;

import net.dv8tion.jda.core.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.the_dodo.REST.service.AdminServiceImpl;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.interfaces.services.IServerService;
import xyz.the_dodo.database.interfaces.services.IUserService;
import xyz.the_dodo.database.types.Admin;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.User;

public class SetAdmin extends IFunction
{
	@Autowired
	private IAdminService m_adminService;

	@Autowired
	private IUserService m_userService;

	@Autowired
	private IServerService m_serverService;

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
					if(UserUtils.userExists(member.getUser())) {
						user = m_userService.findByDiscordId(member.getUser().getId());
						if(ServerUtils.serverExist(p_messageParams.getGuild())) {
							server = m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

							admin = new Admin();

							admin.setServer(server);
							admin.setUser(user);

							admin = m_adminService.save(admin);

							if(admin != null)
								p_messageParams.getTextChannel().sendMessage("User "+p_messageParams.getUser().getAsMention()+" is now ADMIN").queue();
							else
								p_messageParams.getTextChannel().sendMessage("An error occurred!").queue();
						}
					}
				}
			} else
				p_messageParams.getTextChannel().sendMessage("Only admins or the owner of the Guild can add admins!").queue();
		} else
			p_messageParams.getTextChannel().sendMessage("You need to mention users that you want to add as admins!").queue();
	}
}
