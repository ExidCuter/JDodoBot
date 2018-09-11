package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.dodo.fortnite.Fortnite;
import xyz.dodo.fortnite.entity.FortniteData;
import xyz.dodo.fortnite.entity.League;
import xyz.dodo.fortnite.entity.Stat;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

public class FortniteRecords extends IFunction {
    private static Fortnite m_fortnite;

    public FortniteRecords(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    public static void setFortnite(Fortnite p_fortnite) {
        m_fortnite = p_fortnite;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        String name;
        FortniteData data;
        League selectedLeague;
        EmbedBuilder embMsg;

        if (p_messageParams.getParameters().length >= 3) {
            name = StringUtils.glueStringsBackTogether(p_messageParams.getParameters(), "%20", 2);

            data = m_fortnite.getPlayerInfo(p_messageParams.getParameters()[0], name);

            if (data.getResult().isOk()) {
                selectedLeague = data.getLeague(League.getModeFromString(p_messageParams.getParameters()[1].toLowerCase()));

                embMsg = new EmbedBuilder();

                embMsg.setAuthor((data.getPlayer().getNickname() + "'s " + selectedLeague.getMode().name() + " Stats"),
                        "https://fortnitetracker.com/profile/" + p_messageParams.getParameters()[0] + "/" + data.getPlayer().getNickname());
                embMsg.setColor(p_messageParams.getMessage().getMember().getColor());

                for (Stat s : selectedLeague.getStats()) {
                    if (!s.getValue().equals("0"))
                        embMsg.addField(s.getLabel(), s.getValue(), true);
                }

                embMsg.setFooter("© DodoBot | STATS POWERED BY FORTNITETRACKER.COM", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");

                p_messageParams.getTextChannel().sendMessage(embMsg.build()).complete();
            } else
                p_messageParams.getTextChannel().sendMessage("Can't get the data! `" + data.getResult() + "`").queue();
        } else
            p_messageParams.getTextChannel().sendMessage(this.getEmbededHelp().build()).queue();
    }
}
