package xyz.the_dodo.bot.functions.quotes;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.QuoteUtils;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.database.types.Quote;

import java.util.List;

public class GetQuote extends IFunction {
    public GetQuote(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.QUOTES;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (p_messageParams.getParameters().length > 0) {
            Quote randomQuote;
            List<Quote> quotes;

            quotes = QuoteUtils.getQuotesFromUser(p_messageParams.getParameters()[0]);

            if (quotes.size() > 0) {
                randomQuote = quotes.get(RandomGen.rndNm(quotes.size()));

                p_messageParams.getTextChannel().sendMessage("\"" + randomQuote.getQuote() + "\" ~ © " + randomQuote.getPerson() + ", " + randomQuote.getWheno().getYear()).queue();
            } else
                p_messageParams.getTextChannel().sendMessage("No quotes from " + p_messageParams.getParameters()[0]).queue();
        }
    }
}
