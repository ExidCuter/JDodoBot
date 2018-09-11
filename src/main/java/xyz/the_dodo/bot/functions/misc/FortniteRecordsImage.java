package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.dodo.fortnite.Fortnite;
import xyz.dodo.fortnite.entity.FortniteData;
import xyz.dodo.fortnite.entity.League;
import xyz.dodo.fortnite.entity.Stat;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.StringUtils;

import java.io.IOException;

public class FortniteRecordsImage extends IFunction {
    private static Fortnite m_fortnite;

    public FortniteRecordsImage(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    public static void setFortnite(Fortnite p_fortnite) {
        m_fortnite = p_fortnite;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        byte[] file;
        String name;
        FortniteData data;
        League selectedLeague;

        if (p_messageParams.getParameters().length >= 3) {
            name = StringUtils.glueStringsBackTogether(p_messageParams.getParameters(), "%20", 2);

            data = m_fortnite.getPlayerInfo(p_messageParams.getParameters()[0], name);

            if (data.getResult().isOk()) {
                selectedLeague = data.getLeague(League.getModeFromString(p_messageParams.getParameters()[1].toLowerCase()));

                try {
                    file = ImageUtils.generateFortnitePicture(selectedLeague.getStats(), data.getPlayer(), selectedLeague).toByteArray();

                    p_messageParams.getTextChannel().sendFile(file, "fortniteStats.png").queue();
                } catch (IOException e) {
                    p_messageParams.getTextChannel().sendMessage("An error occurred when generating your picture!").queue();
                    e.printStackTrace();
                }
            } else
                p_messageParams.getTextChannel().sendMessage("Can't get the data! `" + data.getResult() + "`").queue();
        } else
            p_messageParams.getTextChannel().sendMessage(this.getEmbededHelp().build()).queue();
    }
}
