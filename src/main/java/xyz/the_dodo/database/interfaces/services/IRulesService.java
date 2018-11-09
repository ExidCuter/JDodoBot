package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.Rules;

public interface IRulesService extends ICRUD<Rules> {
    Rules getRulesByServerDiscordId(String discordId);
}
