package xyz.the_dodo.bot.Functions.stats;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.Stats;

public class CheckStats extends IFunction {
    public CheckStats(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Stats stats;
        Member user;
        String level, img;

        img = "";
        level = "";
        user = p_messageParams.getMessage().getMember();
        stats = StatsUtils.statsExists(p_messageParams.getUser());

        if (stats != null) {
            EmbedBuilder embMsg = new EmbedBuilder();
            embMsg.setColor(user.getColor());
            embMsg.setTitle(user.getEffectiveName() + "'s stats");
            embMsg.setThumbnail(user.getUser().getAvatarUrl());

            if (stats.getNumOfMessages() + stats.getNumOfFiles() < 500) {
                level = "Cleaner Cat";
                img = "https://i.ytimg.com/vi/pID_QuyUi98/maxresdefault.jpg";
            } else if (stats.getNumOfMessages() + stats.getNumOfFiles() < 2000) {
                level = "Street Cat";
                img = "https://images.fineartamerica.com/images-medium-large/street-cat-jordi-angrill.jpg";
            } else if (stats.getNumOfMessages() + stats.getNumOfFiles() < 6000) {
                level = "Domestic Cat";
                img = "http://4.bp.blogspot.com/-TD52tuFneRU/VqkYcAVu6NI/AAAAAAABkBY/6B0Pq6xq3m0/s1600/funny-cats-191-22.jpg";
            } else if (stats.getNumOfMessages() + stats.getNumOfFiles() < 12000) {
                level = "Money Cat";
                img = "http://68.media.tumblr.com/ccc65aa110ae5c5b9af063ea418183d4/tumblr_nrge8hzV3a1qbxi45o7_r1_1280.jpg";
            } else if (stats.getNumOfMessages() + stats.getNumOfFiles() < 25000) {
                level = "Super Cat";
                img = "https://i.ytimg.com/vi/rcMJeTv6P9M/maxresdefault.jpg";
            } else if (stats.getNumOfMessages() + stats.getNumOfFiles() < 40000) {
                level = "Cat roll";
                img = "https://boygeniusreport.files.wordpress.com/2015/06/funny-cat.jpg";
            }

            embMsg.addField("Level", level, false);
            embMsg.addField("Number of messages", String.valueOf(stats.getNumOfMessages()), true);
            embMsg.addField("Number of files uploaded", String.valueOf(stats.getNumOfFiles()), true);
            embMsg.setImage(img);

            p_messageParams.getTextChannel().sendMessage(embMsg.build()).queue();
        } else
            p_messageParams.getTextChannel().sendMessage("No stats to report!").queue();
    }
}
