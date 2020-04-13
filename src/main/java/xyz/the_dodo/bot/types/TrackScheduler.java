package xyz.the_dodo.bot.types;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TrackScheduler extends AudioEventAdapter {
    private boolean repeating = false;
    final AudioPlayer player;
    final Queue<AudioTrack> queue;
    AudioTrack lastTrack;

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public AudioTrack getLastTrack() {
        return lastTrack;
    }

    public Queue<AudioTrack> getQueue() {
        return queue;
    }

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedList<>();
    }

    public void queue(AudioTrack track) {
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void nextTrack() {
        player.startTrack(queue.poll(), false);
    }

    public void clearQueue() {
        queue.clear();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, @NotNull AudioTrackEndReason endReason) {
        this.lastTrack = track;

        if (endReason.mayStartNext) {
            if (repeating)
                player.startTrack(lastTrack.makeClone(), false);
            else
                nextTrack();
        }
    }

    public void shuffle() {
        Collections.shuffle((List<?>) queue);
    }
}
