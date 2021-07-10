package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class UserServiceImplTests extends AbstractTest {
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
				.contains(5L, "00000000000002");

		//update

		user.setDiscordId("00000000000003");

		user = userService.save(user);

		assertThat(user).isNotNull()
				.extracting("id", "discordId")
				.contains(5L, "00000000000003");
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
				.contains(
						tuple(1L, "00000000000000"),
						tuple(2L, "00000000000001"),
						tuple(3L, "666"),
						tuple(user.getId(), user.getDiscordId())
				);

		userService.delete(user);

		users = userService.findAll();

		assertThat(users).isNotNull()
				.extracting("id", "discordId")
				.doesNotContain(
						tuple(user.getId(), user.getDiscordId())
				);
	}
}
