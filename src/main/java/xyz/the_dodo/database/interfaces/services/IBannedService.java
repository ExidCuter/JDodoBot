package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.BannedUser;

import java.util.List;

public interface IBannedService extends ICRUD<BannedUser> {
    List<BannedUser> findByServerDiscordId(String discordId);
    List<BannedUser> findByUserDiscordId(String discordId);
    BannedUser findByUserAndServerDiscordId(String userDiscordId, String serverDiscordId);
}
