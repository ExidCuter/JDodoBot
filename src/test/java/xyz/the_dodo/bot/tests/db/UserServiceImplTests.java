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
import xyz.the_dodo.REST.service.UserServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IUserService;
import xyz.the_dodo.database.types.User;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"/h2-test.properties"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql"})
public class UserServiceImplTests {
	@Autowired
	private IUserRepo userRepo;

	private IUserService userService;

	@PostConstruct
	public void setup() {
		UserServiceImpl service;

		service = new UserServiceImpl();

		service.setUserRepo(userRepo);

		userService = service;
	}

	@Test
	public void test_findAll() {
		List<User> users;

		users = userService.findAll();

		assertThat(users).isNotNull()
				.extracting("id", "discordId")
				.contains(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001")
				);
	}

	@Test
	public void test_findById() {
		User user;

		user = userService.findById(1L);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(1L, "00000000000000");
	}

	@Test
	public void test_findDiscordId() {
		User user;

		user = userService.findByDiscordId("00000000000001");

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(2L, "00000000000001");
	}

	@Test
	public void test_save() {
		User user;

		//insert
		user = new User();

		user.setDiscordId("00000000000002");

		user = userService.save(user);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000002");

		//update

		user.setDiscordId("00000000000003");

		user = userService.save(user);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000003");
	}

	@Test
	public void test_delete() {
		User user;
		List<User> users;

		user = User.builder()
				.discordId("00000000000005")
				.banned(false)
				.build();

		user = userService.save(user);

		users = userService.findAll();

		assertThat(users).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001"),
						tuple(user.getId(), user.getDiscordId())
				);

		userService.delete(user);

		users = userService.findAll();

		assertThat(users).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001")
				);
	}
}
