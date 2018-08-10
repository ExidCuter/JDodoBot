package xyz.the_dodo.tests.service;

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

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({ "/h2-test.properties" })
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql, /testData/bankAccounts.sql"})
public class BankAccoutServiceImplTests
{
	@Autowired
	private IUserRepo m_userRepo;

	@Autowired
	private IBankAccountRepo m_bankAccountRepo;

	private IBankService m_bankService;

	@PostConstruct
	public void setup() {
		BankServiceImpl service;

		service = new BankServiceImpl();

		service.setUserRepo(m_userRepo);
		service.setBankAccountRepo(m_bankAccountRepo);

		m_bankService = service;
	}

	@Test
	public void test_findAll() {
		List<BankAccount> accounts;

		accounts = m_bankService.findAll();

		assertThat(accounts).isNotNull()
				.extracting("id", "user.id")
				.contains(
						tuple(1L, 1L),
						tuple(2L, 2L)
				);
	}

}
