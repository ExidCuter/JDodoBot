package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;

public class Pause extends IFunction {
    public Pause(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        AudioPlayer player;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        player = musicManager.player;

        if (player.getPlayingTrack() != null) {
            player.setPaused(!player.isPaused());
            if (player.isPaused())
                p_messageParams.getTextChannel().sendMessage("The player has been paused.").queue();
            else
                p_messageParams.getTextChannel().sendMessage("The player has resumed playing.").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Cannot pause or resume player because no track is loaded for playing.").queue();
    }
}
