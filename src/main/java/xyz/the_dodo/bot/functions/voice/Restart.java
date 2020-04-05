package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

@BotService(command = "restart", description = "Restarts playing current song/video", usage = "restart", category = CommandCategoryEnum.VOICE)
public class Restart extends IFunction {
    public Restart(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Guild guild;
        AudioTrack track;
        AudioPlayer player;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        player = musicManager.player;
        scheduler = musicManager.scheduler;

        track = player.getPlayingTrack();
        if (track == null)
            track = scheduler.getLastTrack();

        if (track != null) {
            messageParams.getTextChannel().sendMessage("Restarting track: " + track.getInfo().title).queue();
            player.playTrack(track.makeClone());
        } else
            messageParams.getTextChannel().sendMessage("No track has been previously started, so the player cannot replay a track!").queue();
    }
}
