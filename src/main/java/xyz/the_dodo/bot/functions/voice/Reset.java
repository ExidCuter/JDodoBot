package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

public class Reset extends IFunction {
    public Reset(String command, String description, String usage) {
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

        synchronized (DodoBot.getVoiceUtils().musicManagers)
        {
            scheduler.clearQueue();
            player.destroy();
            guild.getAudioManager().setSendingHandler(null);
            DodoBot.getVoiceUtils().musicManagers.remove(guild.getId());
        }

        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);

        guild.getAudioManager().setSendingHandler(musicManager.sendHandler);

        p_messageParams.getTextChannel().sendMessage("The player has been completely reset!").queue();
    }
}
