package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.the_dodo.REST.service.AdminServiceImpl;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

public class AdminUtils
{
	@Autowired
	private static AdminServiceImpl m_adminService;

	public static boolean isAdminOfGuild(User user, Guild guild) {
		List<Admin> admins;

		admins =  m_adminService.getAdminsByServerId(guild.getId());

		for(Admin admin : admins) {
			if(admin.getUser().getDiscordId().equals(user.getId()))
				return true;
		}

		return false;
	}
}
