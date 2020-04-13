package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.DeletedMessage;

import java.util.List;

public interface IDeletedMessageService extends ICRUD<DeletedMessage> {
    List<DeletedMessage> findAllByUserDiscordId(String userId);

    List<DeletedMessage> findAllByServerDiscordId(String serverId);
}
