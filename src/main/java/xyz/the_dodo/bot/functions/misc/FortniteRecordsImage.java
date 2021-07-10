package xyz.the_dodo.bot.functions.misc;

import xyz.dodo.fortnite.Fortnite;
import xyz.dodo.fortnite.entity.FortniteData;
import xyz.dodo.fortnite.entity.League;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.StringUtils;

import java.io.IOException;

@BotService(command = "fortnite.img", description = "Fortnite records image!!!", usage = "!fortnite.img <PLATFORM> <GAMEMODE> <USERNAME>")
public class FortniteRecordsImage extends IFunction {
    private static Fortnite fortnite;

    public FortniteRecordsImage(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    public static void setFortnite(Fortnite fortnite) {
        FortniteRecordsImage.fortnite = fortnite;
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        byte[] file;
        String name;
        FortniteData data;
        League selectedLeague;

        if (messageParams.getParameters().length >= 3) {
            name = StringUtils.glueStringsBackTogether(messageParams.getParameters(), "%20", 2);

            data = fortnite.getPlayerInfo(messageParams.getParameters()[0], name);

            if (data.getResult().isOk()) {
                selectedLeague = data.getLeague(League.getModeFromString(messageParams.getParameters()[1].toLowerCase()));

                try {
                    file = ImageUtils.generateFortnitePicture(selectedLeague.getStats(), data.getPlayer(), selectedLeague).toByteArray();

                    messageParams.getTextChannel().sendFile(file, "fortniteStats.png").queue();
                } catch (IOException e) {
                    messageParams.getTextChannel().sendMessage("An error occurred when generating your picture!").queue();
                    e.printStackTrace();
                }
            } else {
                messageParams.getTextChannel().sendMessage("Can't get the data! `" + data.getResult() + "`").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage(this.getEmbededHelp().build()).queue();
        }

        return this;
    }
}
