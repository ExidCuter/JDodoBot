package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IDeletedMessageRepo;
import xyz.the_dodo.database.interfaces.services.IDeletedMessageService;
import xyz.the_dodo.database.types.DeletedMessage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeletedMessageServiceImpl implements IDeletedMessageService {
    @Autowired
    private IDeletedMessageRepo deletedMessageRepo;

    @Override
    public DeletedMessage findById(long id) {
        return deletedMessageRepo.getOne(id);
    }

    @Override
    public List<DeletedMessage> findAllByUserDiscordId(String userId) {
        return deletedMessageRepo.findAll()
                .stream()
                .filter(deletedMessage -> deletedMessage.getUser().getDiscordId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeletedMessage> findAllByServerDiscordId(String serverId) {
        return deletedMessageRepo.findAll()
                .stream()
                .filter(deletedMessage -> deletedMessage.getServer().getDiscordId().equals(serverId))
                .collect(Collectors.toList());
    }

    @Override
    public List<DeletedMessage> findAll() {
        return deletedMessageRepo.findAll();
    }

    @Override
    public DeletedMessage save(DeletedMessage object) {
        if (object != null)
            return deletedMessageRepo.save(object);

        return null;
    }

    @Override
    public boolean delete(DeletedMessage object) {
        if (object != null) {
            deletedMessageRepo.delete(object);
            return true;
        }

        return false;
    }
}
