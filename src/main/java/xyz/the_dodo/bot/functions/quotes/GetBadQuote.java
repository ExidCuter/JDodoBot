package xyz.the_dodo.bot.functions.quotes;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

public class GetBadQuote extends IFunction {
    public GetBadQuote(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.QUOTES;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        EmbedBuilder embMsg;

        if (p_messageParams.getParameters().length == 0) {
            embMsg = new EmbedBuilder();

            String quote = RedditUtils.getRandomPost(RedditUtils.getPosts("ShittyQuotesPorn", 50));
            String quotes[] = quote.split("&");

            embMsg.setTitle(quotes[0], "https://reddit.com" + quotes[2]);
            embMsg.setImage(quotes[1]);
            embMsg.setFooter("/r/ShittyQuotesPorn", "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
            embMsg.setColor(new Color(0, 176, 253));

            p_messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
        }
    }
}
