package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IPrefixRepo;
import xyz.the_dodo.database.interfaces.services.IPrefixService;
import xyz.the_dodo.database.types.Prefix;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrefixServiceImpl implements IPrefixService {
    @Autowired
    private IPrefixRepo prefixRepo;

    public void setPrefixRepo(IPrefixRepo prefixRepo) {
        this.prefixRepo = prefixRepo;
    }

    @Override
    public Prefix getByServerDiscordId(String discordId) {
        List<Prefix> prefixes = prefixRepo.findAll()
                .stream()
                .filter(prefix -> prefix.getServer().getDiscordId().equals(discordId))
                .collect(Collectors.toList());

        if (prefixes.size() > 0)
            return prefixes.get(0);

        return null;
    }

    @Override
    public Prefix findById(long id) {
        return prefixRepo.getOne(id);
    }

    @Override
    public List<Prefix> findAll() {
        return prefixRepo.findAll();
    }

    @Override
    public Prefix save(Prefix prefix) {
        if (prefix != null)
            return prefixRepo.save(prefix);

        return null;
    }

    @Override
    public boolean delete(Prefix prefix) {
        if (prefix != null) {
            prefixRepo.delete(prefix);
            return true;
        }

        return false;
    }
}
