package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.BankAccount;
import xyz.the_dodo.database.types.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class BankAccoutServiceImplTests extends AbstractTest {

	@Test
	public void test_findAll() {
		List<BankAccount> accounts;

		accounts = bankService.findAll();

		assertThat(accounts).isNotNull()
				.extracting("id", "user.id")
				.contains(
						tuple(1L, 1L),
						tuple(2L, 2L)
				);
	}

	@Test
	public void test_getById() {
		BankAccount ba;

		ba = bankService.findById(1L);

		assertThat(ba).isNotNull()
				.extracting("id", "user.id")
				.contains(1L, 1L);
	}

	@Test
	public void test_getByUserDiscordId() {
		BankAccount ba;

		ba = bankService.findByUserDiscordId("00000000000001");

		assertThat(ba).isNotNull()
				.extracting("id", "user.id")
				.contains(2L, 2L);
	}

	@Test
	public void test_save() {
		//insert
		User user;
		BankAccount ba;

		user = userRepo.findById(1L).get();

		ba = new BankAccount();

		ba.setLastPay(LocalDateTime.now());
		ba.setBalance(666);
		ba.setUser(user);

		ba = bankService.save(ba);

		List<BankAccount> bas = bankService.findAll();

		assertThat(ba).isNotNull()
                .extracting("id", "user.id", "balance")
                .contains(5L, 1L, 666.0);

		//update
		ba = new BankAccount();
		ba.setId(1L);
		ba.setUser(user);
		ba.setBalance(666.0);
		ba.setLastPay(LocalDateTime.now());

		ba = bankService.save(ba);

        assertThat(ba).isNotNull()
                .extracting("id", "user.id", "balance")
                .contains(1L, 1L, 666.0);
	}

	@Test
	public void test_delete() {
		User user;
		BankAccount ba;
		List<BankAccount> bas;

		user = userRepo.findById(2L).get();

		ba = new BankAccount();

		ba.setLastPay(LocalDateTime.now());
		ba.setBalance(666);
		ba.setUser(user);

		ba = bankService.save(ba);

		bas = bankService.findAll();

        assertThat(bas).isNotNull()
                .extracting("id", "user.id")
                .contains(
                        tuple(1L, 1L),
                        tuple(2L, 2L),
                        tuple(3L, 3L),
                        tuple(6L, 2L)
                );

		bankService.delete(ba);

		bas = bankService.findAll();

        assertThat(bas).isNotNull()
                .extracting("id", "user.id")
                .doesNotContain(
                        tuple(6L, 2L)
                );

    }
}
