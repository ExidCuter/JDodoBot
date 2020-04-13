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
import xyz.the_dodo.REST.service.ServerServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IServerRepo;
import xyz.the_dodo.database.interfaces.services.IServerService;
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
public class ServerServiceImplTests {
	@Autowired
	private IServerRepo m_serverRepo;

	private IServerService m_serverService;

	@PostConstruct
	public void setup() {
		ServerServiceImpl service;

		service = new ServerServiceImpl();

		service.setServerRepo(m_serverRepo);

		m_serverService = service;
	}

	@Test
	public void test_findAll() {
		List<Server> servers;

		servers = m_serverService.findAll();

		assertThat(servers).isNotNull()
				.extracting("id", "discordId")
				.contains(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001")
				);
	}

	@Test
	public void test_findById() {
		Server server;

		server = m_serverService.findById(1L);

		assertThat(server).isNotNull()
				.extracting("id", "discordId")
				.contains(1L, "00000000000000");
	}

	@Test
	public void test_findDiscordId() {
		Server server;

		server = m_serverService.findByDiscordId("00000000000001");

		assertThat(server).isNotNull()
				.extracting("id", "discordId")
				.contains(2L, "00000000000001");
	}

	@Test
	public void test_save() {
		Server server;

		//insert
		server = new Server();

		server.setDiscordId("00000000000002");

		server = m_serverService.save(server);

		assertThat(server).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000002");

		//update

		server.setDiscordId("00000000000003");

		server = m_serverService.save(server);

		assertThat(server).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000003");
	}

	@Test
	public void test_delete() {
		Server server;
		Server serverToDelete;
		List<Server> servers;

		serverToDelete = new Server();
		serverToDelete.setId(4L);

		server = Server.builder().discordId("00000000000003").saveDeleted(false).build();
		server.setId(4L);

		m_serverService.save(server);

		servers = m_serverService.findAll();

		assertThat(servers).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001"),
						tuple(4L, "00000000000003")
				);


		m_serverService.delete(server);

		servers = m_serverService.findAll();

		assertThat(servers).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001")
				);
	}
}
