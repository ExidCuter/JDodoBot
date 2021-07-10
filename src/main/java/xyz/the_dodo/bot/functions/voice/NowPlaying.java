package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.EmbedMessageUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "nowPlaying", description = "Displays what is currently playing", usage = "nowPlaying", category = CommandCategoryEnum.VOICE)
public class NowPlaying extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public NowPlaying(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        AudioPlayer player;
        AudioTrack currentTrack;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        player = musicManager.player;
        currentTrack = player.getPlayingTrack();

        if (currentTrack != null) {
            messageParams.getTextChannel().sendMessage(EmbedMessageUtils.getNowPlayingMessage(currentTrack).build()).queue();
        } else {
            messageParams.getTextChannel().sendMessage("**The player is not currently playing anything!**").queue();
        }

        return this;
    }
}
