package xyz.the_dodo.bot.Functions.quotes;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.QuoteUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.Quote;

public class CreateQuote extends IFunction {
    public CreateQuote(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Quote quote;
        String who, what;

        if (p_messageParams.getParameters().length > 1) {
            who = p_messageParams.getParameters()[0];
            what = StringUtils.glueStringsBackTogether(p_messageParams.getParameters(), " ", 1);

            what = what.replace("\"", "");

            if (!what.endsWith("."))
                what += ".";

            what = StringUtils.capitaliseFirsLetter(what);

            quote = QuoteUtils.saveQuoteFromUser(what, who);

            p_messageParams.getTextChannel().sendMessage("\"" + quote.getQuote() + "\" ~ © " + quote.getPerson() + ", " + quote.getWheno().getYear()).queue();
        }
    }
}
