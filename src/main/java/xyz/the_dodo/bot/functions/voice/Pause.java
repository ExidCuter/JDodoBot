package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "pause", description = "Pauses the player", category = CommandCategoryEnum.VOICE)
public class Pause extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Pause(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Guild guild;
        AudioPlayer player;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        player = musicManager.player;

        if (player.getPlayingTrack() != null) {
            player.setPaused(!player.isPaused());
            if (player.isPaused())
                messageParams.getTextChannel().sendMessage("The player has been paused.").queue();
            else
                messageParams.getTextChannel().sendMessage("The player has resumed playing.").queue();
        } else
            messageParams.getTextChannel().sendMessage("Cannot pause or resume player because no track is loaded for playing.").queue();
    }
}
