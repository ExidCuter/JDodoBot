package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IDefaultRoleRepo;
import xyz.the_dodo.database.interfaces.services.IDefaultRoleService;
import xyz.the_dodo.database.types.DefaultRole;

import java.util.List;

@Service
public class DefaultRoleServiceImpl implements IDefaultRoleService {
    @Autowired
    private IDefaultRoleRepo defaultRoleRepo;

    public void setDefaultRoleRepo(IDefaultRoleRepo defaultRoleRepo) {
        this.defaultRoleRepo = defaultRoleRepo;
    }

    @Override
    public DefaultRole findById(long id) {
        return defaultRoleRepo.getOne(id);
    }

    @Override
    public List<DefaultRole> findAll() {
        return defaultRoleRepo.findAll();
    }

    @Override
    public DefaultRole findByServerId(Long serverId) {
        return defaultRoleRepo.getDefaultRoleByServerId(serverId);
    }

    @Override
    public DefaultRole save(DefaultRole defaultRole) {
        if (defaultRole != null)
            return defaultRoleRepo.save(defaultRole);

        return null;
    }

    @Override
    public boolean delete(DefaultRole defaultRole) {
        if (defaultRole != null) {
            defaultRoleRepo.deleteById(defaultRole.getId());
            return true;
        }

        return false;
    }
}
