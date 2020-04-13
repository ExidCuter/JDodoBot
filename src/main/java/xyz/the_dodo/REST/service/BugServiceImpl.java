package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IBugReportRepo;
import xyz.the_dodo.database.interfaces.services.IBugService;
import xyz.the_dodo.database.types.BugReport;

import java.util.List;

@Service
public class BugServiceImpl implements IBugService {
    @Autowired
    private IBugReportRepo bugReportRepo;

    @Override
    public BugReport findById(long id) {
        return bugReportRepo.getOne(id);
    }

    @Override
    public List<BugReport> findAll() {
        return bugReportRepo.findAll();
    }

    @Override
    public BugReport save(BugReport bugReport) {
        if (bugReport != null)
            return bugReportRepo.save(bugReport);

        return null;
    }

    @Override
    public boolean delete(BugReport bugReport) {
        return false;
    }
}
