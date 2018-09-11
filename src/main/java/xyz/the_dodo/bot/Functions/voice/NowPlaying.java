package xyz.the_dodo.bot.Functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;

import static xyz.the_dodo.bot.utils.VoiceUtils.getTimestamp;

public class NowPlaying extends IFunction {
    public NowPlaying(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        AudioPlayer player;
        AudioTrack currentTrack;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        player = musicManager.player;
        currentTrack = player.getPlayingTrack();

        if (currentTrack != null)
        {
            String title = currentTrack.getInfo().title;
            String position = getTimestamp(currentTrack.getPosition());
            String duration = getTimestamp(currentTrack.getDuration());

            String nowplaying = String.format("**Playing:** %s\n**Time:** [%s / %s]",
                    title, position, duration);

            p_messageParams.getTextChannel().sendMessage(nowplaying).queue();
        }
        else
            p_messageParams.getTextChannel().sendMessage("The player is not currently playing anything!").queue();
    }
}
