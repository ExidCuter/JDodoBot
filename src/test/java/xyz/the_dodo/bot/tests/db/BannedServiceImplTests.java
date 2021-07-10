package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.BannedUser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class BannedServiceImplTests extends AbstractTest {
    @Test
    public void test_findAll() {
        List<BannedUser> bannedUsers;

        bannedUsers = bannedService.findAll();

        assertThat(bannedUsers).isNotNull().extracting("id", "server.id", "user.id").contains(
                tuple(1L, 1L, 2L)
        );
    }

    @Test
    public void test_findById() {
        BannedUser bannedUser;

        bannedUser = bannedService.findById(1L);

        assertThat(bannedUser).isNotNull().extracting("id", "server.id", "user.id").contains(1L, 1L, 2L);
    }

    @Test
    public void test_findByServer() {
        List<BannedUser> bannedUsers;

        bannedUsers = bannedService.findByServerDiscordId("00000000000000");

        assertThat(bannedUsers).isNotNull().extracting("id", "server.id", "user.id").contains(
                tuple(1L, 1L, 2L)
        );
    }

    @Test
    public void test_findByUser() {
        List<BannedUser> bannedUsers;

        bannedUsers = bannedService.findByUserDiscordId("00000000000001");

        assertThat(bannedUsers).isNotNull().extracting("id", "server.id", "user.id").contains(
                tuple(1L, 1L, 2L)
        );
    }

    @Test
    public void test_findByUserAndServer() {
        BannedUser bannedUser;

        bannedUser = bannedService.findByUserAndServerDiscordId("00000000000001", "00000000000000");

        assertThat(bannedUser).isNotNull().extracting("id", "server.id", "user.id").contains(1L, 1L, 2L);
    }
}
