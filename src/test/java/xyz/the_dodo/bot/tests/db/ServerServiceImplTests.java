package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.Server;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ServerServiceImplTests extends AbstractTest {
	@Test
	public void test_findAll() {
		List<Server> servers;

		servers = serverService.findAll();

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

		server = serverService.findById(1L);

		assertThat(server).isNotNull()
				.extracting("id", "discordId")
				.contains(1L, "00000000000000");
	}

	@Test
	public void test_findDiscordId() {
		Server server;

		server = serverService.findByDiscordId("00000000000001");

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

		server = serverService.save(server);

		assertThat(server).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000002");

		//update

		server.setDiscordId("00000000000003");

		server = serverService.save(server);

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

		serverService.save(server);

		servers = serverService.findAll();

		assertThat(servers).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001"),
						tuple(4L, "00000000000003")
				);


		serverService.delete(server);

		servers = serverService.findAll();

		assertThat(servers).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001")
				);
	}
}
