package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

public class Stop extends IFunction {
    public Stop(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        AudioPlayer player;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        player = musicManager.player;
        scheduler = musicManager.scheduler;

        scheduler.clearQueue();

        player.stopTrack();
        player.setPaused(false);

        p_messageParams.getTextChannel().sendMessage("Playback has been completely stopped and the queue has been cleared.").queue();
    }
}
