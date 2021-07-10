package xyz.the_dodo.bot.functions.quotes;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.QuoteUtils;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.database.types.Quote;

import java.util.List;

@BotService(command = "quote", description = "Gets a quote from person", usage = "quote <PERSON>", category = CommandCategoryEnum.QUOTES)
public class GetQuote extends IFunction {
    public GetQuote(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        if (messageParams.getParameters().length > 0) {
            Quote randomQuote;
            List<Quote> quotes;

            quotes = QuoteUtils.getQuotesFromUser(messageParams.getParameters()[0]);

            if (quotes.size() > 0) {
                randomQuote = quotes.get(RandomGen.rndNm(quotes.size()));

                messageParams.getTextChannel().sendMessage("\"" + randomQuote.getQuote() + "\" ~ © " + randomQuote.getPerson() + ", " + randomQuote.getWhen().getYear()).queue();
            } else
                messageParams.getTextChannel().sendMessage("No quotes from " + messageParams.getParameters()[0]).queue();
        }

        return this;
    }
}
