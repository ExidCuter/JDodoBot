package xyz.the_dodo.tests.service;

import org.junit.Ignore;
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
@TestPropertySource({ "/h2-test.properties" })
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql"})
public class UserServiceImplTests {
	@Autowired
	private IUserRepo m_userRepo;

	private IUserService m_userService;

	@PostConstruct
	public void setup() {
		UserServiceImpl service;

		service = new UserServiceImpl();

		service.setUserRepo(m_userRepo);

		m_userService = service;
	}

	@Test
	public void test_findAll() {
		List<User> users;

		users = m_userService.findAll();

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

		user = m_userService.findById(1L);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(1L, "00000000000000");
	}

	@Test
	public void test_findDiscordId() {
		User user;

		user = m_userService.findByDiscordId("00000000000001");

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

		user = m_userService.save(user);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000002");

		//update

		user.setDiscordId("00000000000003");

		user = m_userService.save(user);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(3L, "00000000000003");
	}

	@Test
	@Ignore
	public void test_delete() {
		User user;
		List<User> users;

		user = new User();

		user.setId(2L);

		m_userService.delete(user);

		users = m_userService.findAll();

		assertThat(users).isNotNull()
				.extracting("id", "discordId")
				.containsExactly(
						tuple(1L, "00000000000000")
				);
	}
}
