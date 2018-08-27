package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.the_dodo.database.interfaces.services.IUserService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

public class UserUtils
{
	@Autowired
	private static IUserService m_userService;

	public static boolean userExists(User user) {
		xyz.the_dodo.database.types.User dodoUser;

		dodoUser = m_userService.findByDiscordId(user.getId());

		return dodoUser != null;
	}

	public static void createDodoUser(User p_user) {
		xyz.the_dodo.database.types.User dodoUser;

		dodoUser = new xyz.the_dodo.database.types.User();

		dodoUser.setDiscordId(p_user.getId());

		m_userService.save(dodoUser);
	}
}
