package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class DefaultRoleImplTests extends AbstractTest {
    @Test
    public void Test_getAllDefaultRoles() {
        List<DefaultRole> roles;

        roles = defaultRoleService.findAll();

        assertThat(roles).isNotNull()
                .extracting("id", "server.id", "discordId")
                .contains(
                        tuple(1L, 1L, "0000000000000"),
                        tuple(2L, 2L, "0000000000001")
                );
    }

    @Test
    public void Test_getByServerId() {
        DefaultRole role;

        role = defaultRoleService.findByServerId(1L);

        assertThat(role).isNotNull()
                .extracting("id", "server.id", "discordId")
                .contains(1L, 1L, "0000000000000");
    }

    @Test
    public void Test_save() {
        //insert
        Server server;
        DefaultRole defaultRole;

        server = new Server();
        server.setId(2L);
        server.setDiscordId("0000000000001");

        defaultRole = new DefaultRole();

        defaultRole.setId(3L);
        defaultRole.setDiscordId("0000000000023");
        defaultRole.setServer(server);

        defaultRoleService.save(defaultRole);

        defaultRole = defaultRoleService.findById(3L);

        assertThat(defaultRole).isNotNull()
                .extracting("id", "server.id", "discordId")
                .contains(3L, 2L, "0000000000023");
    }
}
