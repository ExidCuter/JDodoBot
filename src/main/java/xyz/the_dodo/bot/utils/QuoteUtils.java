package xyz.the_dodo.bot.utils;

import xyz.the_dodo.REST.service.QuoteServiceImpl;
import xyz.the_dodo.database.interfaces.services.IQuoteService;
import xyz.the_dodo.database.types.Quote;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuoteUtils {
    public static IQuoteService quoteService = BeanUtils.getBean(QuoteServiceImpl.class);

    public static boolean userHasQuotes(String user) {
        List<Quote> quotes;

        quotes = quoteService.findAll();

        for (Quote quote : quotes) {
            if (quote.getPerson().equals(user))
                return true;
        }

        return false;
    }

    public static List<Quote> getQuotesFromUser(String user) {
        List<Quote> quotes, usersQuotes;

        quotes = quoteService.findAll();
        usersQuotes = new ArrayList<>();

        quotes.forEach(quote -> {
            if (quote.getPerson().equals(user))
                usersQuotes.add(quote);
        });

        return usersQuotes;
    }

    public static Quote saveQuoteFromUser(String newQuote, String person) {
        Quote quote;

        quote = new Quote();

        quote.setPerson(person);
        quote.setQuote(newQuote);
        quote.setWhen(LocalDate.now());

        quoteService.save(quote);

        return quote;
    }

}
