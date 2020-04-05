package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.dodo.fortnite.Fortnite;
import xyz.dodo.fortnite.entity.FortniteData;
import xyz.dodo.fortnite.entity.League;
import xyz.dodo.fortnite.entity.Stat;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

@BotService(command = "fortnite", description = "Fortnite records!", usage = "fortnite <PLATFORM> <GAMEMODE> <USERNAME>")
public class FortniteRecords extends IFunction {
    private static Fortnite fortnite;

    public FortniteRecords(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    public static void setFortnite(Fortnite fortnite) {
        FortniteRecords.fortnite = fortnite;
    }

    @Override
    public void trigger(MessageParams messageParams) {
        String name;
        FortniteData data;
        League selectedLeague;
        EmbedBuilder embMsg;

        if (messageParams.getParameters().length >= 3) {
            name = StringUtils.glueStringsBackTogether(messageParams.getParameters(), "%20", 2);

            data = fortnite.getPlayerInfo(messageParams.getParameters()[0], name);

            if (data.getResult().isOk()) {
                selectedLeague = data.getLeague(League.getModeFromString(messageParams.getParameters()[1].toLowerCase()));

                embMsg = new EmbedBuilder();

                embMsg.setAuthor((data.getPlayer().getNickname() + "'s " + selectedLeague.getMode().name() + " Stats"),
                        "https://fortnitetracker.com/profile/" + messageParams.getParameters()[0] + "/" + data.getPlayer().getNickname());
                embMsg.setColor(messageParams.getMessage().getMember().getColor());

                for (Stat s : selectedLeague.getStats()) {
                    if (!s.getValue().equals("0"))
                        embMsg.addField(s.getLabel(), s.getValue(), true);
                }

                embMsg.setFooter("© DodoBot | STATS POWERED BY FORTNITETRACKER.COM", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");

                messageParams.getTextChannel().sendMessage(embMsg.build()).complete();
            } else
                messageParams.getTextChannel().sendMessage("Can't get the data! `" + data.getResult() + "`").queue();
        } else
            messageParams.getTextChannel().sendMessage(this.getEmbededHelp().build()).queue();
    }
}
