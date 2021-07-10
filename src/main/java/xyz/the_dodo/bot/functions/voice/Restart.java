package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.types.audio.TrackScheduler;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.EmbedMessageUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "restart", description = "Restarts playing current song/video", usage = "restart", category = CommandCategoryEnum.VOICE)
public class Restart extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Restart(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        AudioTrack track;
        AudioPlayer player;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        player = musicManager.player;
        scheduler = musicManager.scheduler;
        track = player.getPlayingTrack();

        if (track == null) {
            track = scheduler.getLastTrack();
        }

        if (track != null) {
            messageParams.getTextChannel().sendMessage(EmbedMessageUtils.getRestartSongMessage(track).build()).queue();
            player.playTrack(track.makeClone());
        } else {
            messageParams.getTextChannel().sendMessage("No track has been previously started, so the player cannot replay a track!").queue();
        }

        return this;
    }
}
