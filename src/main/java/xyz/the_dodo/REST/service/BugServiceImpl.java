package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IBugReportRepo;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.interfaces.services.IBugService;
import xyz.the_dodo.database.types.BankAccount;
import xyz.the_dodo.database.types.BugReport;

import java.util.List;

@Service
public class BugServiceImpl implements IBugService {
    @Autowired
    private IBugReportRepo m_bugReportRepo;

    @Override
    public BugReport findById(long id) {
        return m_bugReportRepo.getOne(id);
    }

    @Override
    public List<BugReport> findAll() {
        return m_bugReportRepo.findAll();
    }

    @Override
    public BugReport save(BugReport bugReport) {
        if(bugReport != null)
            return m_bugReportRepo.save(bugReport);

        return null;
    }

    @Override
    public BugReport save(BugReport oldObject, BugReport newObject)
    {
        if(oldObject != null && newObject != null && oldObject.getId() == newObject.getId())
            return m_bugReportRepo.save(newObject);

        return null;
    }

    @Override
    public boolean delete(BugReport bugReport) {
        return false;
    }
}
