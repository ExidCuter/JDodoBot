package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.Subscription;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class SubServiceImplTests extends AbstractTest {
    @Test
    public void test_findAll() {
        List<Subscription> subscriptions;

        subscriptions = subService.findAll();

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

        subscriptions = subService.getAllSubscriptionOfServerDiscordId("00000000000000");

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

        subscriptions = subService.getSubscriptionsToTrigger(6);

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

        server = serverRepo.findById(2L).get();

        subscription = new Subscription();

        subscription.setTimer(6);
        subscription.setServer(server);
        subscription.setChannelId("id");
        subscription.setCommand("new command");

        subService.save(subscription);

        subscription = subService.findById(4L);

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

        subService.delete(subscription);

        subscriptions = subService.findAll();

        assertThat(subscriptions).isNotNull()
                .extracting("id", "server.discordId", "command")
                .contains(
                        tuple(1L, "00000000000000", "this is the first command"),
                        tuple(2L, "00000000000000", "this is the second command")
                );
    }
}
