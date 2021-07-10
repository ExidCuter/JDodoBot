package xyz.the_dodo.bot.tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.the_dodo.REST.service.*;
import xyz.the_dodo.bot.utils.*;
import xyz.the_dodo.database.interfaces.repos.*;
import xyz.the_dodo.database.interfaces.services.*;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"/h2-test.properties"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql", "/testData/bankAccounts.sql", "/testData/servers.sql", "/testData/admins.sql", "/testData/bannedUsers.sql", "/testData/quotes.sql"})
public abstract class AbstractTest {
    @Autowired
    protected IUserRepo userRepo;

    @Autowired
    protected IBankAccountRepo bankAccountRepo;

    @Autowired
    protected IServerRepo serverRepo;

    @Autowired
    protected IAdminoRepo adminRepo;

    @Autowired
    protected IBannedUserRepo bannedUserRepo;

    @Autowired
    protected IDefaultRoleRepo defaultRoleRepo;

    @Autowired
    protected IPrefixRepo prefixRepo;

    @Autowired
    protected IQuoteRepo quoteRepo;

    @Autowired
    protected ISubRepo subRepo;

    @Autowired
    protected IStatsRepo statsRepo;

    protected IAdminService adminService;
    protected IBankService bankService;
    protected IBannedService bannedService;
    protected IDefaultRoleService defaultRoleService;
    protected IUserService userService;
    protected IServerService serverService;
    protected IPrefixService prefixService;
    protected IQuoteService quoteService;
    protected ISubService subService;
    protected IStatsService statsService;

    @PostConstruct
    public void setupStats() {
        StatsServiceImpl service;

        service = new StatsServiceImpl();

        service.setStatsRepo(statsRepo);

        statsService = service;
        StatsUtils.statsService = service;
    }

    @PostConstruct
    public void setupSub() {
        SubServiceImpl service;

        service = new SubServiceImpl();

        service.setSubRepo(subRepo);

        subService = service;
        SubsUtils.subService = service;
    }

    @PostConstruct
    public void setupQuotes() {
        QuoteServiceImpl service;

        service = new QuoteServiceImpl();

        service.setQuoteRepo(quoteRepo);

        quoteService = service;
        QuoteUtils.quoteService = service;
    }

    @PostConstruct
    public void setupPrefix() {
        PrefixServiceImpl service;

        service = new PrefixServiceImpl();

        service.setPrefixRepo(prefixRepo);

        prefixService = service;
        PrefixUtils.prefixService = service;
    }

    @PostConstruct
    public void setupDefaultRole() {
        DefaultRoleServiceImpl service;

        service = new DefaultRoleServiceImpl();

        service.setDefaultRoleRepo(defaultRoleRepo);

        defaultRoleService = service;
        DefaultRoleUtils.defaultRoleService = service;
    }

    @PostConstruct
    public void setupBanned() {
        BannedServiceImpl service = new BannedServiceImpl();

        service.setBannedUserRepo(bannedUserRepo);

        bannedService = service;
        BannedUtils.bannedService = service;
    }

    @PostConstruct
    public void setupAdmin() {
        AdminServiceImpl service;

        service = new AdminServiceImpl();

        service.setAdminRepo(adminRepo);

        adminService = service;
        AdminUtils.adminService = service;
    }

    @PostConstruct
    public void setupBank() {
        BankServiceImpl bankService;

        bankService = new BankServiceImpl();

        bankService.setBankAccountRepo(bankAccountRepo);

        this.bankService = bankService;
        BankUtils.bankService = bankService;
    }

    @PostConstruct
    public void setupServer() {
        ServerServiceImpl service;

        service = new ServerServiceImpl();

        service.setServerRepo(serverRepo);

        serverService = service;
        ServerUtils.serverService = service;
    }

    @PostConstruct
    public void setupUser() {
        UserServiceImpl service;

        service = new UserServiceImpl();

        service.setUserRepo(userRepo);

        userService = service;
        UserUtils.userService = service;
    }
}
