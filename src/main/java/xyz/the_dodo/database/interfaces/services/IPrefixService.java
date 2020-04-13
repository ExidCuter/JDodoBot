package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.Prefix;

public interface IPrefixService extends ICRUD<Prefix> {
    Prefix getByServerDiscordId(String discordId);
}
