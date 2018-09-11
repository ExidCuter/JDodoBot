package xyz.the_dodo.bot.Functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

public class Play extends IFunction {
    public Play(String command, String description, String usage) {
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

        if (p_messageParams.getParameters().length > 0) { //Commands has 2 parts, .play and url.
            DodoBot.getVoiceUtils().loadAndPlay(musicManager, p_messageParams.getMessage().getChannel(), p_messageParams.getParameters()[0], false);
        } else { //It is only the command to start playback (probably after pause)
            if (player.isPaused()) {
                player.setPaused(false);

                p_messageParams.getTextChannel().sendMessage("Playback as been resumed.").queue();
            } else if (player.getPlayingTrack() != null)
                p_messageParams.getTextChannel().sendMessage("Player is already playing!").queue();
            else if (scheduler.isEmpty())
                p_messageParams.getTextChannel().sendMessage("The current audio queue is empty! Add something to the queue first!").queue();
        }
    }
}
