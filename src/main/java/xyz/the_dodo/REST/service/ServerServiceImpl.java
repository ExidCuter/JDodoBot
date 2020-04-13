package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IServerRepo;
import xyz.the_dodo.database.interfaces.services.IServerService;
import xyz.the_dodo.database.types.Server;

import java.util.List;

@Service
public class ServerServiceImpl implements IServerService {
    @Autowired
    private IServerRepo serverRepo;

    public void setServerRepo(IServerRepo serverRepo) {
        this.serverRepo = serverRepo;
    }

    @Override
    public Server findById(long id) {
        return serverRepo.getOne(id);
    }

    @Override
    public List<Server> findAll() {
        return serverRepo.findAll();
    }

    @Override
    public Server findByDiscordId(String discordId) {
        return serverRepo.findByDiscordId(discordId);
    }

    @Override
    public Server save(Server server) {
        if (server != null) {
            return serverRepo.save(server);
        }

        return null;
    }

    @Override
    public boolean delete(Server server) {
        if (server != null) {
            serverRepo.deleteById(server.getId());
            return true;
        }

        return false;
    }
}
