package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.types.BankAccount;
import xyz.the_dodo.database.interfaces.repos.IBankAccountRepo;

import java.util.List;

@Service
public class BankServiceImpl implements IBankService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IBankAccountRepo bankAccountRepo;

    public void setUserRepo(IUserRepo p_userRepo)
    {
        userRepo = p_userRepo;
    }

    public void setBankAccountRepo(IBankAccountRepo p_bankAccountRepo)
    {
        bankAccountRepo = p_bankAccountRepo;
    }

    @Override
    public BankAccount findById(long id) {
        return bankAccountRepo.getOne(id);
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepo.findAll();
    }

    @Override
    public BankAccount findByUserDiscordId(String discordId)
    {
        return bankAccountRepo.findByUserDiscordId(discordId);
    }

    @Override
    public BankAccount save(BankAccount ba) {
        if(ba != null){
            return bankAccountRepo.save(ba);
        }
        return null;
    }

    @Override
    public BankAccount save(BankAccount oldObject, BankAccount newObject)
    {
        if(oldObject != null && newObject != null && oldObject.getId() == newObject.getId())
            return bankAccountRepo.save(newObject);

        return null;
    }

    @Override
    public boolean delete(BankAccount ba){
        if(ba != null) {
            bankAccountRepo.deleteById(ba.getId());
            return true;
        }
        return false;
    }
}
