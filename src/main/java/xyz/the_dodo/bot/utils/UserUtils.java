package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.REST.service.UserServiceImpl;
import xyz.the_dodo.database.interfaces.services.IUserService;

public class UserUtils
{
	public static IUserService m_userService = BeanUtils.getBean(UserServiceImpl.class);

	public static boolean userExists(User p_user) {
		xyz.the_dodo.database.types.User user;

		user = m_userService.findByDiscordId(p_user.getId());

		return user != null;
	}

	public static void createDodoUser(User p_user) {
		xyz.the_dodo.database.types.User user;

		user = new xyz.the_dodo.database.types.User();

		user.setDiscordId(p_user.getId());

		m_userService.save(user);
	}
}
