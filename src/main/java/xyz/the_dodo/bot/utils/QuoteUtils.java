package xyz.the_dodo.bot.utils;

import xyz.the_dodo.REST.service.QuoteServiceImpl;
import xyz.the_dodo.database.interfaces.services.IQuoteService;
import xyz.the_dodo.database.types.Quote;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuoteUtils {
    public static IQuoteService m_quoteService = BeanUtils.getBean(QuoteServiceImpl.class);

    public static boolean userHasQuotes(String user) {
        List<Quote> quotes;

        quotes = m_quoteService.findAll();

        for (Quote quote : quotes) {
            if (quote.getPerson().equals(user))
                return true;
        }

        return false;
    }

    public static List<Quote> getQuotesFromUser(String user) {
        List<Quote> quotes, usersQuotes;

        quotes = m_quoteService.findAll();
        usersQuotes = new ArrayList<>();

        quotes.forEach(p_quote -> {
            if (p_quote.getPerson().equals(user))
                usersQuotes.add(p_quote);
        });

        return usersQuotes;
    }

    public static Quote saveQuoteFromUser(String p_quote, String p_user) {
        Quote quote;

        quote = new Quote();

        quote.setPerson(p_user);
        quote.setQuote(p_quote);
        quote.setWheno(LocalDate.now());

        m_quoteService.save(quote);

        return quote;
    }

}
