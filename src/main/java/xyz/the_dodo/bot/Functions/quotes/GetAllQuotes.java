package xyz.the_dodo.bot.Functions.quotes;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.QuoteUtils;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.Quote;

import java.util.List;

public class GetAllQuotes extends IFunction {
    public GetAllQuotes(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.QUOTES;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        List<Quote> quotes;
        List<String> messages;
        StringBuilder builder;

        quotes = QuoteUtils.getQuotesFromUser(p_messageParams.getParameters()[0]);

        if (quotes.size() > 0) {
            builder = new StringBuilder();

            quotes.forEach(p_quote -> builder.append("\"" + p_quote.getQuote() + "\" ~ © " + p_quote.getPerson() + ", " + p_quote.getWheno().getYear() + "\n"));

            messages = StringUtils.splitIntoMessages(builder.toString(), '\n');

            messages.forEach(p_message -> p_messageParams.getTextChannel().sendMessage(p_message).queue());
        } else
            p_messageParams.getTextChannel().sendMessage("No quotes from " + p_messageParams.getParameters()[0]).queue();
    }
}
