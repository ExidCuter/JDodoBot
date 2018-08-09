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
    private IServerRepo m_serverRepo;

    public void setServerRepo(IServerRepo p_serverRepo)
    {
        m_serverRepo = p_serverRepo;
    }

    @Override
    public Server findById(long id) {
        return m_serverRepo.getOne(id);
    }

    @Override
    public List<Server> findAll() {
        return m_serverRepo.findAll();
    }

    @Override
    public Server findByDiscordId(String discordId)
    {
        return m_serverRepo.findByDiscordId(discordId);
    }

    @Override
    public Server save(Server server) {
        if(server != null) {
            return m_serverRepo.save(server);
        }

        return null;
    }

    @Override
    public Server save(Server oldObject, Server newObject)
    {
        if(oldObject != null && newObject != null && oldObject.getId() == newObject.getId()){
            return m_serverRepo.save(newObject);
        }

        return null;
    }

    @Override
    public boolean delete(Server server) {
        if(server != null){
            m_serverRepo.deleteById(server.getId());
            return true;
        }

        return false;
    }
}
