package xyz.the_dodo.tests.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.the_dodo.REST.service.SubServiceImpl;
import xyz.the_dodo.database.interfaces.repos.ISubRepo;
import xyz.the_dodo.database.interfaces.services.ISubService;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.Subscription;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({ "/h2-test.properties" })
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/servers.sql"})
public class SubServiceImplTests {
    @Autowired
    private ISubRepo m_subRepo;

    private ISubService m_subService;

    @PostConstruct
    public void setup() {
        SubServiceImpl service;

        service = new SubServiceImpl();

        service.setSubRepo(m_subRepo);

        m_subService = service;
    }
    @Test
    public void test_findAll() {
        List<Subscription> subscriptions;

        subscriptions = m_subService.findAll();

        assertThat(subscriptions).isNotNull()
                .extracting("id", "server.discordId", "command")
                .contains(
                        tuple(1L, "00000000000000", "this is the first command"),
                        tuple(2L, "00000000000000", "this is the second command"),
                        tuple(3L, "00000000000001", "this is the third command")
                );
    }


    @Test
    public void test_getSubsOfByGuildId() {
        List<Subscription> subscriptions;

        subscriptions = m_subService.getAllSubscriptionOfServerDiscordId("00000000000000");

        assertThat(subscriptions).isNotNull()
                .extracting("id", "server.discordId", "command")
                .contains(
                        tuple(1L, "00000000000000", "this is the first command"),
                        tuple(2L, "00000000000000", "this is the second command")
                );
    }

    @Test
    public void test_getSubsToTrigger() {
        List<Subscription> subscriptions;

        subscriptions = m_subService.getSubscriptionsToTrigger(6);

        assertThat(subscriptions).isNotNull()
                .extracting("id", "server.discordId", "command", "timer")
                .contains(
                        tuple(1L, "00000000000000", "this is the first command", 6),
                        tuple(3L, "00000000000001", "this is the third command", 6)
                );
    }

    @Test
    public void test_save() {
        Server server;
        Subscription subscription;

        server = new Server();
        subscription = new Subscription();

        server.setId(1L);
        server.setDiscordId("00000000000001");

        subscription.setTimer(6);
        subscription.setServer(server);
        subscription.setChannelId("id");
        subscription.setCommand("new command");

        m_subService.save(subscription);

        subscription  = m_subService.findById(4L);

        assertThat(subscription).isNotNull()
                .extracting("id", "server.discordId", "command", "timer")
                .contains(4L, "00000000000001", "new command", 6);
    }

    @Test
    public void test_delete() {
        Subscription subscription;
        List<Subscription> subscriptions;

        subscription = new Subscription();

        subscription.setId(3L);

        m_subService.delete(subscription);

        subscriptions = m_subService.findAll();

        assertThat(subscriptions).isNotNull()
                .extracting("id", "server.discordId", "command")
                .contains(
                        tuple(1L, "00000000000000", "this is the first command"),
                        tuple(2L, "00000000000000", "this is the second command")
                );
    }
}
