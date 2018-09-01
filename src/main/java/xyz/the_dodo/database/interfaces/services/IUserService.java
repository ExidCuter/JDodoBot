package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.User;

public interface IUserService extends ICRUD<User> {
	User findByDiscordId(String discordId);
}
