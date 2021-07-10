package xyz.the_dodo.bot.functions.voice;

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

import java.util.Queue;

@BotService(command = "listPlaying", description = "Lists all the items in the play queue!", usage = "listPlaying", category = CommandCategoryEnum.VOICE)
public class ListQueue extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public ListQueue(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        scheduler = musicManager.scheduler;

        Queue<AudioTrack> queue = scheduler.getQueue();
        synchronized (queue) {
            if (queue.isEmpty()) {
                messageParams.getTextChannel().sendMessage("The queue is currently empty!").queue();
            } else {
                messageParams.getTextChannel().sendMessage(EmbedMessageUtils.getMusicQueueMessage(queue).build()).queue();
            }
        }

        return this;
    }
}
