package xyz.the_dodo.bot.functions.quotes;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.QuoteUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.Quote;

import java.util.List;

@BotService(command = "allQuotes", description = "Gets all of the persons quotes", usage = "allQuotes <PERSON>", category = CommandCategoryEnum.QUOTES)
public class GetAllQuotes extends IFunction {
    public GetAllQuotes(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        List<Quote> quotes;
        List<String> messages;
        StringBuilder builder;

        quotes = QuoteUtils.getQuotesFromUser(messageParams.getParameters()[0]);

        if (quotes.size() > 0) {
            builder = new StringBuilder();

            quotes.forEach(p_quote -> builder.append("\"" + p_quote.getQuote() + "\" ~ © " + p_quote.getPerson() + ", " + p_quote.getWheno().getYear() + "\n"));

            messages = StringUtils.splitIntoMessages(builder.toString(), '\n');

            messages.forEach(p_message -> messageParams.getTextChannel().sendMessage(p_message).queue());
        } else
            messageParams.getTextChannel().sendMessage("No quotes from " + messageParams.getParameters()[0]).queue();
    }
}
