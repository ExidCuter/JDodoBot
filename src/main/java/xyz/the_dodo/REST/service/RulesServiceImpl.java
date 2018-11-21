package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IRulesRepo;
import xyz.the_dodo.database.interfaces.services.IRulesService;
import xyz.the_dodo.database.types.Rules;

import java.util.List;

@Service
public class RulesServiceImpl implements IRulesService {
    @Autowired
    IRulesRepo m_rulesRepo;

    @Override
    public Rules getRulesByServerDiscordId(String discordId) {
        Rules rules;
        try {
            rules = m_rulesRepo.findAll().stream().filter(p_rules -> p_rules.getServer().getDiscordId().equals(discordId)).findAny().get();
        } catch (Exception e) {
            return null;
        }
        return rules;
    }

    @Override
    public Rules findById(long id) {
        return m_rulesRepo.getOne(id);
    }

    @Override
    public List<Rules> findAll() {
        return m_rulesRepo.findAll();
    }

    @Override
    public Rules save(Rules object) {
        if(object != null) {
            return m_rulesRepo.save(object);
        }

        return null;
    }

    @Override
    public boolean delete(Rules object) {
        if (object != null){
            m_rulesRepo.deleteById(object.getId());
            return true;
        }
        return false;
    }
}
