package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

public class RedditRandomPost extends IFunction {

    public RedditRandomPost(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        String post;
        String[] postParts;
        EmbedBuilder embMsg;

        if (p_messageParams.getParameters().length > 0) {

            post = RedditUtils.getRandomPost(RedditUtils.getPosts(p_messageParams.getParameters()[0], 50));
            if (!post.isEmpty()) {
                postParts = post.split("&");

                embMsg = new EmbedBuilder();

                embMsg.setTitle(postParts[0], "https://reddit.com" + postParts[2]);
                embMsg.setImage(postParts[1]);
                embMsg.setFooter("/r/" + p_messageParams.getParameters()[0], "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
                embMsg.setColor(new Color(253, 130, 0));
                p_messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
            } else
                p_messageParams.getTextChannel().sendMessage("No posts found!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Wrong parameters! Please specify a subreddit!").queue();

    }
}
