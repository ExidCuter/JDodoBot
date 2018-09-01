package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.Server;

public interface IServerService extends ICRUD<Server> {
	Server findByDiscordId(String discordId);
}
