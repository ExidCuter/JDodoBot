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
import xyz.the_dodo.REST.service.DefaultRoleServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IDefaultRoleRepo;
import xyz.the_dodo.database.interfaces.repos.IServerRepo;
import xyz.the_dodo.database.interfaces.services.IDefaultRoleService;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"/h2-test.properties"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/servers.sql"})
public class DefaultRoleImplTests {
    @Autowired
    private IServerRepo m_serverRepo;

    @Autowired
    private IDefaultRoleRepo m_defaultRoleRepo;

    private IDefaultRoleService m_defaultRoleService;

    @PostConstruct
    public void setup() {
        DefaultRoleServiceImpl service;

        service = new DefaultRoleServiceImpl();

        //service.setServerRepo(m_serverRepo);
        service.setDefaultRoleRepo(m_defaultRoleRepo);

        m_defaultRoleService = service;
    }

    @Test
    public void Test_getAllDefaultRoles() {
        List<DefaultRole> roles;

        roles = m_defaultRoleService.findAll();

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

        role = m_defaultRoleService.findByServerId(1L);

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

        m_defaultRoleService.save(defaultRole);

        defaultRole = m_defaultRoleService.findById(3L);

        assertThat(defaultRole).isNotNull()
                .extracting("id", "server.id", "discordId")
                .contains(3L, 2L, "0000000000023");
    }
}
