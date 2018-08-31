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
import xyz.the_dodo.REST.service.StatsServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IStatsRepo;
import xyz.the_dodo.database.interfaces.services.IStatsService;
import xyz.the_dodo.database.types.Stats;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({ "/h2-test.properties" })
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/users.sql"})
public class StatsServiceImplTests {

    @Autowired
    private IStatsRepo m_statsRepo;

    private IStatsService m_statsService;

    @PostConstruct
    public void setup() {
        StatsServiceImpl service;

        service = new StatsServiceImpl();

        service.setStatsRepo(m_statsRepo);

        m_statsService = service;
    }

    @Test
    public void Test_findAll() {
        List<Stats> stats;

        stats = m_statsService.findAll();

        assertThat(stats).isNotNull()
                .extracting("id", "numOfMessages", "numOfFiles", "user.id")
                .contains(
                        tuple(1L, 10L, 1L, 1L),
                        tuple(2L, 12L, 2L, 2L)
                );
    }
}

