package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.the_dodo.REST.service.BannedServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IBannedUserRepo;
import xyz.the_dodo.database.interfaces.services.IBannedService;
import xyz.the_dodo.database.types.BannedUser;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"/h2-test.properties"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql", "/testData/servers.sql", "/testData/bannedUsers.sql"})
public class BannedServiceImplTests {
    @Autowired
    private IBannedUserRepo m_bannedUserRepo;

    private IBannedService m_bannedService;

    @PostConstruct
    public void setup() {
        BannedServiceImpl service = new BannedServiceImpl();

        service.setBannedUserRepo(m_bannedUserRepo);

        m_bannedService = service;
    }

    @Test
    public void test_findAll() {
        List<BannedUser> bannedUsers;

        bannedUsers = m_bannedService.findAll();

        assertThat(bannedUsers).isNotNull().extracting("id", "server.id", "user.id").contains(
                tuple(1L, 1L, 2L)
        );
    }

    @Test
    public void test_findById() {
        BannedUser bannedUser;

        bannedUser = m_bannedService.findById(1L);

        assertThat(bannedUser).isNotNull().extracting("id", "server.id", "user.id").contains(1L, 1L, 2L);
    }

    @Test
    public void test_findByServer() {
        List<BannedUser> bannedUsers;

        bannedUsers = m_bannedService.findByServerDiscordId("00000000000000");

        assertThat(bannedUsers).isNotNull().extracting("id", "server.id", "user.id").contains(
                tuple(1L, 1L, 2L)
        );
    }

    @Test
    public void test_findByUser() {
        List<BannedUser> bannedUsers;

        bannedUsers = m_bannedService.findByUserDiscordId("00000000000001");

        assertThat(bannedUsers).isNotNull().extracting("id", "server.id", "user.id").contains(
                tuple(1L, 1L, 2L)
        );
    }

    @Test
    public void test_findByUserAndServer() {
        BannedUser bannedUser;

        bannedUser = m_bannedService.findByUserAndServerDiscordId("00000000000001", "00000000000000");

        assertThat(bannedUser).isNotNull().extracting("id", "server.id", "user.id").contains(1L, 1L, 2L);
    }
}
