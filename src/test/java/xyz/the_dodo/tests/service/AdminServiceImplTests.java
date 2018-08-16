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
import xyz.the_dodo.REST.service.AdminServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IAdminoRepo;
import xyz.the_dodo.database.interfaces.repos.IServerRepo;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({ "/h2-test.properties" })
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql", "/testData/servers.sql", "/testData/admins.sql"})
public class AdminServiceImplTests
{
	@Autowired
	private IAdminoRepo m_adminRepo;

	@Autowired
	private IUserRepo m_userRepo;

	@Autowired
	private IServerRepo m_serverRepo;

	private IAdminService m_adminService;

	@PostConstruct
	public void setup() {
		AdminServiceImpl service;

		service = new AdminServiceImpl();

		service.setUserRepo(m_userRepo);
		service.setAdminRepo(m_adminRepo);
		service.setServerRepo(m_serverRepo);

		m_adminService = service;
	}

	@Test
	public void test_findAll() {
		List<Admin> admins;

		admins = m_adminService.findAll();

		assertThat(admins).isNotNull()
				.extracting("id", "server.id", "user.id")
				.contains(
						tuple(1L, 1L, 1L),
						tuple(2L, 2L, 1L)
				);
	}
}
