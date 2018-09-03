package xyz.the_dodo.bot.Functions.misc;

import com.github.jreddit.entity.Submission;
import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

public class RedditTopPosts extends IFunction {
    public RedditTopPosts(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        EmbedBuilder embMsg;

        if (p_messageParams.getParameters().length > 0) {
            for (Submission s : RedditUtils.getPosts(p_messageParams.getParameters()[0], 1)) {
                embMsg = new EmbedBuilder();

                embMsg.setTitle(s.getTitle(), "https://reddit.com" + s.getPermalink());
                embMsg.setImage(s.getURL());
                embMsg.setFooter("/r/" + p_messageParams.getParameters()[0], "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
                embMsg.setColor(new Color(253, 0, 5));
                p_messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
            }
        } else
            p_messageParams.getTextChannel().sendMessage("Invalid parameters! Please specify a subreddit!").queue();

    }
}
