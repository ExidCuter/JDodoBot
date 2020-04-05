package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

import java.util.Queue;

import static xyz.the_dodo.bot.utils.VoiceUtils.getTimestamp;

@BotService(command = "play", description = "Plays a song or resumes playing", usage = "play || play <SONG LINK>", category = CommandCategoryEnum.VOICE)
public class ListQueue extends IFunction {
    public ListQueue(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Guild guild;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;
        int trackCount;
        long queueLength;

        guild = messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        scheduler = musicManager.scheduler;

        Queue<AudioTrack> queue = scheduler.getQueue();
        synchronized (queue) {
            if (queue.isEmpty())
                messageParams.getTextChannel().sendMessage("The queue is currently empty!").queue();
            else {
                trackCount = 0;
                queueLength = 0;

                StringBuilder sb = new StringBuilder();

                sb.append("Current Queue: Entries: ").append(queue.size()).append("\n");

                for (AudioTrack track : queue) {
                    queueLength += track.getDuration();
                    if (trackCount < 10) {
                        sb.append("`[").append(getTimestamp(track.getDuration())).append("]` ");
                        sb.append(track.getInfo().title).append("\n");
                        trackCount++;
                    }
                }

                sb.append("\n").append("Total Queue Time Length: ").append(getTimestamp(queueLength));

                messageParams.getTextChannel().sendMessage(sb.toString()).queue();
            }
        }
    }
}
