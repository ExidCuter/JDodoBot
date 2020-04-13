package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "play", description = "Plays a song or resumes playing", usage = "play || play <SONG LINK>", category = CommandCategoryEnum.VOICE)
public class Play extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Play(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Guild guild;
        AudioPlayer player;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        player = musicManager.player;
        scheduler = musicManager.scheduler;

        if (messageParams.getParameters().length > 0) {
            voiceUtils.loadAndPlay(musicManager, messageParams.getMessage().getChannel(), messageParams.getParameters()[0], false);
        } else {
            if (player.isPaused()) {
                player.setPaused(false);

                messageParams.getTextChannel().sendMessage("Playback as been resumed.").queue();
            } else if (player.getPlayingTrack() != null)
                messageParams.getTextChannel().sendMessage("Player is already playing!").queue();
            else if (scheduler.isEmpty())
                messageParams.getTextChannel().sendMessage("The current audio queue is empty! Add something to the queue first!").queue();
        }
    }
}
