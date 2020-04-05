package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

@BotService(command = "reddit.random", description = "Gets random post from hot section of the specified subreddit!", usage = "reddit.random <SUBREDDIT NAME>")
public class RedditRandomPost extends IFunction {
    public RedditRandomPost(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        String post;
        String[] postParts;
        EmbedBuilder embMsg;

        if (messageParams.getParameters().length > 0) {

            post = RedditUtils.getRandomPost(RedditUtils.getPosts(messageParams.getParameters()[0], 50));
            if (!post.isEmpty()) {
                postParts = post.split("&");

                embMsg = new EmbedBuilder();

                embMsg.setTitle(postParts[0], "https://reddit.com" + postParts[2]);
                embMsg.setImage(postParts[1]);
                embMsg.setFooter("/r/" + messageParams.getParameters()[0], "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
                embMsg.setColor(new Color(253, 130, 0));
                messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
            } else
                messageParams.getTextChannel().sendMessage("No posts found!").queue();
        } else
            messageParams.getTextChannel().sendMessage("Wrong parameters! Please specify a subreddit!").queue();
    }
}
