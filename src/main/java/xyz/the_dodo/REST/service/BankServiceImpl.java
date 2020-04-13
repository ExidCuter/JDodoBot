package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IBankAccountRepo;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.types.BankAccount;

import java.util.List;

@Service
public class BankServiceImpl implements IBankService {
    @Autowired
    private IBankAccountRepo bankAccountRepo;

    public void setBankAccountRepo(IBankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
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
    public BankAccount findByUserDiscordId(String discordId) {
        return bankAccountRepo.findByUserDiscordId(discordId);
    }

    @Override
    public BankAccount save(BankAccount ba) {
        if (ba != null) {
            return bankAccountRepo.save(ba);
        }

        return null;
    }

    @Override
    public boolean delete(BankAccount ba) {
        if (ba != null) {
            bankAccountRepo.deleteById(ba.getId());
            return true;
        }

        return false;
    }
}
