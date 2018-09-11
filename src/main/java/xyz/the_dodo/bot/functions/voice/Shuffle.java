package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

public class Shuffle extends IFunction {
    public Shuffle(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        scheduler = musicManager.scheduler;

        if (!scheduler.isEmpty()) {
            scheduler.shuffle();
            p_messageParams.getTextChannel().sendMessage("The queue has been shuffled!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("The queue is currently empty!").queue();
    }
}
