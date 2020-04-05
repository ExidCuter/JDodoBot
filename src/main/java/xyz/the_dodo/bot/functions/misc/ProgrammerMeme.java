package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RedditUtils;

import java.awt.*;

@BotService(command = "reddit.getMeme();", description = "Gets a meme from r/programmerHumor", usage = "reddit.getMeme();")
public class ProgrammerMeme extends IFunction {
    public ProgrammerMeme(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        String meme;
        String[] memeParts;
        EmbedBuilder embMsg;

        embMsg = new EmbedBuilder();

        meme = RedditUtils.getRandomPost(RedditUtils.getPosts("programmerhumor", 50));
        memeParts = meme.split("&");

        embMsg.setTitle(memeParts[0], "https://reddit.com" + memeParts[2]);
        embMsg.setImage(memeParts[1]);
        embMsg.setFooter("/r/programmerhumor", "https://media.glassdoor.com/sqll/796358/reddit-squarelogo-1490630845152.png");
        embMsg.setColor(new Color(247, 253, 0));

        messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
    }
}
