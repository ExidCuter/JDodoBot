package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.Stats;

public interface IStatsService extends ICRUD<Stats> {
    Stats getByUserDiscordId(String discordId);
}
