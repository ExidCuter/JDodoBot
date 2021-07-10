package xyz.the_dodo.bot.functions.misc;

import com.github.jreddit.entity.Submission;
import net.dv8tion.jda.api.EmbedBuilder;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

@BotService(command = "reddit.getTop", description = "Gets top 3 posts form hot from specified subreddit!", usage = "reddit.getTop <SUBREDDIT NAME>")
public class RedditTopPosts extends IFunction {
    public RedditTopPosts(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        EmbedBuilder embMsg;

        if (messageParams.getParameters().length > 0) {
            for (Submission s : RedditUtils.getPosts(messageParams.getParameters()[0], 1)) {
                embMsg = new EmbedBuilder();

                embMsg.setTitle(s.getTitle(), "https://reddit.com" + s.getPermalink());
                embMsg.setImage(s.getURL());
                embMsg.setFooter("/r/" + messageParams.getParameters()[0], "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
                embMsg.setColor(new Color(253, 0, 5));
                messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
            }
        } else
            messageParams.getTextChannel().sendMessage("Invalid parameters! Please specify a subreddit!").queue();

        return this;
    }
}
