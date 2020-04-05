package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

@BotService(command = "shuffle", description = "Shuffles the playing queue", usage = "shuffle", category = CommandCategoryEnum.VOICE)
public class Shuffle extends IFunction {
    public Shuffle(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
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

        if (!scheduler.isEmpty()) {
            scheduler.shuffle();
            messageParams.getTextChannel().sendMessage("The queue has been shuffled!").queue();
        } else
            messageParams.getTextChannel().sendMessage("The queue is currently empty!").queue();
    }
}
