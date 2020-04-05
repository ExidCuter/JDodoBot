package xyz.the_dodo.bot.functions.quotes;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.QuoteUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.Quote;

@BotService(command = "createQuote", description = "Creates a quote", usage = "quote <WHO> <QUOTE>", category = CommandCategoryEnum.QUOTES)
public class CreateQuote extends IFunction {
    public CreateQuote(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Quote quote;
        String who, what;

        if (messageParams.getParameters().length > 1) {
            who = messageParams.getParameters()[0];
            what = StringUtils.glueStringsBackTogether(messageParams.getParameters(), " ", 1);

            what = what.replace("\"", "");

            if (!what.endsWith("."))
                what += ".";

            what = StringUtils.capitaliseFirsLetter(what);

            quote = QuoteUtils.saveQuoteFromUser(what, who);

            messageParams.getTextChannel().sendMessage("\"" + quote.getQuote() + "\" ~ © " + quote.getPerson() + ", " + quote.getWheno().getYear()).queue();
        }
    }
}
