package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IAdminoRepo;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAdminoRepo adminRepo;

    public void setAdminRepo(IAdminoRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin findById(long id) {
        return adminRepo.getOne(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepo.findAll();
    }

    @Override
    public List<Admin> getAdminsByServerId(String discordId) {
        List<Admin> admins;

        admins = adminRepo.findAll();

        admins = admins.stream().filter(admin -> admin.getServer().getDiscordId().equals(discordId)).collect(Collectors.toList());

        return admins;
    }

    @Override
    public Admin save(Admin object) {
        if (object != null) {
            return adminRepo.save(object);
        }

        return null;
    }

    @Override
    public boolean delete(Admin object) {
        if (object != null) {
            adminRepo.deleteById(object.getId());
            return true;
        }

        return false;
    }
}
