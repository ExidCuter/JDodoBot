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
import xyz.the_dodo.REST.service.QuoteServiceImpl;
import xyz.the_dodo.database.interfaces.repos.IQuoteRepo;
import xyz.the_dodo.database.interfaces.services.IQuoteService;
import xyz.the_dodo.database.types.Quote;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource({"/h2-test.properties"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql({"/testData/quotes.sql"})
public class QuoteServiceImplTests {
    @Autowired
    private IQuoteRepo quoteRepo;

    private IQuoteService quoteService;

    @PostConstruct
    public void setup() {
        QuoteServiceImpl service;

        service = new QuoteServiceImpl();

        service.setQuoteRepo(quoteRepo);

        quoteService = service;
    }

    @Test
    public void test_findAll() {
        List<Quote> quotes;

        quotes = quoteService.findAll();

        assertThat(quotes).isNotNull()
                .extracting("id", "person", "quote")
                .contains(
                        tuple(1L, "Janez", "Bolš se je sabo pelat kt dobr peš it."),
                        tuple(2L, "dodo", "Distributacija.")
                );
    }

    @Test
    public void test_findById() {
        Quote quote;

        quote = quoteService.findById(2L);

        assertThat(quote).isNotNull()
                .extracting("id", "person", "quote")
                .contains(2L, "dodo", "Distributacija.");
    }


    @Test
    public void test_save() {
        Quote quote;

        //insert
        quote = new Quote();

        quote.setPerson("dodo");
        quote.setQuote("insert");
        quote.setWhen(LocalDate.now());

        quote = quoteService.save(quote);

        assertThat(quote).isNotNull()
                .extracting("id", "person", "quote")
                .contains(3L, "dodo", "insert");

        //update

        quote.setPerson("dodo");
        quote.setQuote("update");
        quote.setWhen(LocalDate.now());

        quote = quoteService.save(quote);

        assertThat(quote).isNotNull()
                .extracting("id", "person", "quote")
                .contains(3L, "dodo", "update");
    }

    @Test
    public void test_delete() {
        Quote quote;
        List<Quote> quotes;

        quote = new Quote();

        quote.setId(1L);

        quoteService.delete(quote);

        quotes = quoteService.findAll();

        assertThat(quotes).isNotNull()
                .extracting("id", "person", "quote")
                .containsExactly(
                        tuple(2L, "dodo", "Distributacija.")
                );
    }
}
