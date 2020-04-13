package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.Subscription;

import java.util.List;

public interface ISubService extends ICRUD<Subscription> {
    List<Subscription> getAllSubscriptionOfServerDiscordId(String discordId);

    List<Subscription> getSubscriptionsToTrigger(int tick);
}
