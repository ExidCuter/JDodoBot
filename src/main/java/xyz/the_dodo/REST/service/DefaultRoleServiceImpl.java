package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IDefaultRoleRepo;
import xyz.the_dodo.database.interfaces.repos.IServerRepo;
import xyz.the_dodo.database.interfaces.services.IDefaultRoleService;
import xyz.the_dodo.database.types.DefaultRole;

import java.util.List;

@Service
public class DefaultRoleServiceImpl implements IDefaultRoleService {
    @Autowired
    private IServerRepo m_serverRepo;

    @Autowired
    private IDefaultRoleRepo m_defaultRoleRepo;

    public void setServerRepo(IServerRepo p_serverRepo)
    {
        m_serverRepo = p_serverRepo;
    }

    public void setDefaultRoleRepo(IDefaultRoleRepo p_defaultRoleRepo)
    {
        m_defaultRoleRepo = p_defaultRoleRepo;
    }

    @Override
    public DefaultRole findById(long id) {
        return m_defaultRoleRepo.getOne(id);
    }

    @Override
    public List<DefaultRole> findAll() {
        return m_defaultRoleRepo.findAll();
    }

    @Override
    public DefaultRole findByServerId(Long serveriId)
    {
        return m_defaultRoleRepo.getDefaultRoleByServerId(serveriId);
    }

    @Override
    public DefaultRole save(DefaultRole defaultRole) {
        if(defaultRole != null)
            return m_defaultRoleRepo.save(defaultRole);

        return null;
    }

    @Override
    public DefaultRole save(DefaultRole oldObject, DefaultRole newObject)
    {
        if(oldObject != null && newObject != null && oldObject.getId() == newObject.getId())
            return m_defaultRoleRepo.save(newObject);

        return null;

    }

    @Override
    public boolean delete(DefaultRole defaultRole) {
        if(defaultRole != null) {
            m_defaultRoleRepo.deleteById(defaultRole.getId());
            return true;
        }

        return false;
    }
}
