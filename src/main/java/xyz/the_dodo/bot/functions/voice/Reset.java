package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.types.audio.TrackScheduler;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "reset", description = "Resets the player", usage = "reset", category = CommandCategoryEnum.VOICE)
public class Reset extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Reset(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        AudioPlayer player;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        player = musicManager.player;
        scheduler = musicManager.scheduler;

        synchronized (voiceUtils.getMusicManagers()) {
            scheduler.clearQueue();
            player.destroy();
            guild.getAudioManager().setSendingHandler(null);
            voiceUtils.getMusicManagers().remove(guild.getId());
        }

        musicManager = voiceUtils.getMusicManager(guild);

        guild.getAudioManager().setSendingHandler(musicManager.sendHandler);

        messageParams.getTextChannel().sendMessage("The player has been completely reset!").queue();

        return this;
    }
}
