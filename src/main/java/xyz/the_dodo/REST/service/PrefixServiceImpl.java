package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.the_dodo.database.interfaces.repos.IPrefixRepo;
import xyz.the_dodo.database.interfaces.services.IPrefixService;
import xyz.the_dodo.database.types.Prefix;

import java.util.List;

public class PrefixServiceImpl implements IPrefixService {
    @Autowired
    private IPrefixRepo m_prefixRepo;

    @Override
    public Prefix getByServerDiscordId(String p_discordId) {
        return m_prefixRepo.findAll().stream().filter(p_prefix -> p_prefix.getServer().getDiscordId().equals(p_discordId)).findFirst().get();
    }

    @Override
    public Prefix findById(long id) {
        return m_prefixRepo.getOne(id);
    }

    @Override
    public List<Prefix> findAll() {
        return m_prefixRepo.findAll();
    }

    @Override
    public Prefix save(Prefix object) {
        if (object != null)
            return m_prefixRepo.save(object);

        return null;
    }

    @Override
    public boolean delete(Prefix object) {
        if (object != null) {
            m_prefixRepo.delete(object);
            return true;
        }

        return false;
    }
}
