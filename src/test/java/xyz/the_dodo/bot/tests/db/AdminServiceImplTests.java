package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AdminServiceImplTests extends AbstractTest {
	@Test
	public void test_findAll() {
		List<Admin> admins;

		admins = adminService.findAll();

		assertThat(admins).isNotNull()
				.extracting("id", "server.id", "user.id")
				.contains(
						tuple(1L, 1L, 1L),
						tuple(2L, 2L, 1L)
				);
	}
}
