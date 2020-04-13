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
import xyz.the_dodo.REST.service.BankServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IBankAccountRepo;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.types.BankAccount;
import xyz.the_dodo.database.types.User;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"/h2-test.properties"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql", "/testData/bankAccounts.sql"})
public class BankAccoutServiceImplTests {
	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private IBankAccountRepo bankAccountRepo;

	private IBankService bankService;

	@PostConstruct
	public void setup() {
		BankServiceImpl service;

		service = new BankServiceImpl();

		service.setBankAccountRepo(bankAccountRepo);

		bankService = service;
	}

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
				.containsExactly(3L, 1L, 666.0);

		//update
		ba = new BankAccount();
		ba.setId(1L);
		ba.setUser(user);
		ba.setBalance(666.0);
		ba.setLastPay(LocalDateTime.now());

		ba = bankService.save(ba);

		assertThat(ba).isNotNull()
				.extracting("id", "user.id", "balance")
				.containsExactly(1L, 1L, 666.0);
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
				.containsOnly(
						tuple(1L, 1L),
						tuple(2L, 2L),
						tuple(4L, 2L)
				);

		bankService.delete(ba);

		bas = bankService.findAll();

		assertThat(bas).isNotNull()
				.extracting("id", "user.id")
				.containsOnly(
						tuple(1L, 1L),
						tuple(2L, 2L)
				);
	}
}
