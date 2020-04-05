package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

@BotService(command = "stop", description = "Stops playing and clears the queue", usage = "stop", category = CommandCategoryEnum.VOICE)
public class Repeat extends IFunction {
    public Repeat(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Guild guild;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        scheduler = musicManager.scheduler;

        scheduler.setRepeating(!scheduler.isRepeating());
        messageParams.getTextChannel().sendMessage("Player was set to: **" + (scheduler.isRepeating() ? "repeat" : "not repeat") + "**").queue();
    }
}
