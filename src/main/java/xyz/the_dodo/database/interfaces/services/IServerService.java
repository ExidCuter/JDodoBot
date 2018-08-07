package xyz.the_dodo.database.interfaces.services;

import org.springframework.stereotype.Service;
import xyz.the_dodo.database.types.Server;

import java.util.List;


public interface IServerService extends ICRUD<Server> {
	Server findByDiscordId(String discordId);
}
