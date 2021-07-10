package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.Prefix;
import xyz.the_dodo.database.types.Server;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class PrefixServiceImplTests extends AbstractTest {
    @Test
    public void test_findAll() {
        List<Prefix> prefixes;

        prefixes = prefixService.findAll();

        assertThat(prefixes).isNotNull()
                .extracting("id", "server.id", "prefix")
                .contains(
                        tuple(1L, 1L, "#"),
                        tuple(2L, 2L, "_")
                );
    }

    @Test
    public void test_findByServerId() {
        Prefix prefix;

        prefix = prefixService.getByServerDiscordId("00000000000000");

        assertThat(prefix).isNotNull()
                .extracting("id", "server.id", "prefix")
                .contains(1L, 1L, "#");
    }

    @Test
    public void test_save() {
        Server server;
        Prefix prefix;

        prefix = new Prefix();
        server = serverRepo.findById(2L).get();

        prefix.setPrefix("%");
        prefix.setServer(server);

        prefixService.save(prefix);

        prefix = prefixService.findById(3L);

        assertThat(prefix).isNotNull()
                .extracting("id", "server.id", "prefix")
                .contains(3L, 2L, "%");
    }
}
