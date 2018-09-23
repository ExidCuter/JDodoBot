package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

import java.util.Queue;

import static xyz.the_dodo.bot.utils.VoiceUtils.getTimestamp;

public class ListQueue extends IFunction {
    public ListQueue(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;
        int trackCount;
        long queueLength;

        guild = p_messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        scheduler = musicManager.scheduler;

        Queue<AudioTrack> queue = scheduler.getQueue();
        synchronized (queue) {
            if (queue.isEmpty())
                p_messageParams.getTextChannel().sendMessage("The queue is currently empty!").queue();
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

                p_messageParams.getTextChannel().sendMessage(sb.toString()).queue();
            }
        }
    }
}
