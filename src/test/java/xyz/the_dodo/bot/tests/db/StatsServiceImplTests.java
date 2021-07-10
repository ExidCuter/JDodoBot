package xyz.the_dodo.bot.tests.db;

import org.junit.Test;
import xyz.the_dodo.bot.tests.AbstractTest;
import xyz.the_dodo.database.types.Stats;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class StatsServiceImplTests extends AbstractTest {
    @Test
    public void Test_findAll() {
        List<Stats> stats;

        stats = statsService.findAll();

        assertThat(stats).isNotNull()
                .extracting("id", "numOfMessages", "numOfFiles", "user.id")
                .contains(
                        tuple(1L, 10L, 1L, 1L),
                        tuple(2L, 12L, 2L, 2L)
                );
    }
}

