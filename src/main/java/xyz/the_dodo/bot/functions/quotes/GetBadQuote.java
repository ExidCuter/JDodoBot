package xyz.the_dodo.bot.functions.quotes;

import net.dv8tion.jda.api.EmbedBuilder;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

@BotService(command = "badQuote", description = "Gets a quote from reddit", usage = "quote", category = CommandCategoryEnum.QUOTES)
public class GetBadQuote extends IFunction {
    public GetBadQuote(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        EmbedBuilder embMsg;

        if (messageParams.getParameters().length == 0) {
            embMsg = new EmbedBuilder();

            String quote = RedditUtils.getRandomPost(RedditUtils.getPosts("ShittyQuotesPorn", 50));
            String quotes[] = quote.split("&");

            embMsg.setTitle(quotes[0], "https://reddit.com" + quotes[2]);
            embMsg.setImage(quotes[1]);
            embMsg.setFooter("/r/ShittyQuotesPorn", "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
            embMsg.setColor(new Color(0, 176, 253));

            messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
        }

        return this;
    }
}
