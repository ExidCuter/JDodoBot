package xyz.the_dodo.bot.utils;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.jetbrains.annotations.NotNull;
import org.td_fl.youtube.models.Video;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static xyz.the_dodo.bot.utils.VoiceUtils.getTimestamp;

public class EmbedMessageUtils {
    private static final int MAX_VIDEOS = 8;

    @NotNull
    public static EmbedBuilder getPlayEmbedMessage(@NotNull final AudioTrack track, @NotNull final Member member, Video video) {
        EmbedBuilder embMsg = new EmbedBuilder();

        embMsg.setAuthor("Adding to queue:");
        embMsg.setTitle(track.getInfo().title, track.getInfo().uri);
        embMsg.addField("Duration", VoiceUtils.getTimestamp(track.getDuration()), true);
        embMsg.addField("Author", track.getInfo().author, true);

        embMsg.addField("Requested by", member.getAsMention(), true);

        if (video != null) {
            embMsg.setThumbnail(video.getImgUri());
        }

        EmbedMessageUtils.setColorByProvider(embMsg, track.getInfo().uri);

        return embMsg;
    }

    public static EmbedBuilder getYoutubeSearchMessage(@NotNull List<Video> videos, String query) throws UnsupportedEncodingException {
        EmbedBuilder embMsg = new EmbedBuilder();

        embMsg.setAuthor("Search results for:");
        embMsg.setTitle(query, "https://duckduckgo.com/?q=" + URLEncoder.encode(query, "UTF-8") + "&iax=videos&ia=videos");
        embMsg.setColor(new Color(229, 39, 45));

        AtomicInteger curr = new AtomicInteger();
        StringBuilder stringBuilder = new StringBuilder();

        videos.forEach(video -> {
            if (curr.get() == MAX_VIDEOS)
                return;

            stringBuilder.append("`[")
                    .append(curr.get() + 1)
                    .append("]` **")
                    .append(video.getTitle())
                    .append("** - ")
                    .append(video.getChannel())
                    .append(" `[")
                    .append(video.getDuration())
                    .append("]`\n");

            curr.getAndIncrement();
        });

        embMsg.setDescription(stringBuilder.toString());

        return embMsg;
    }

    @NotNull
    public static EmbedBuilder getNowPlayingMessage(@NotNull final AudioTrack track) {
        EmbedBuilder embMsg = new EmbedBuilder();

        embMsg.setAuthor("Currently playing:");
        embMsg.setTitle(track.getInfo().title, track.getInfo().uri);
        embMsg.addField("Duration", VoiceUtils.getTimestamp(track.getDuration()), true);
        embMsg.addField("Author", track.getInfo().author, true);

        String position = getTimestamp(track.getPosition());
        String duration = getTimestamp(track.getDuration());

        embMsg.addField("Play Time", String.format("[%s / %s]", position, duration), true);

        EmbedMessageUtils.setColorByProvider(embMsg, track.getInfo().uri);

        return embMsg;
    }

    @NotNull
    public static EmbedBuilder getRestartSongMessage(@NotNull AudioTrack track) {
        EmbedBuilder embMsg = new EmbedBuilder();

        embMsg.setAuthor("Restarting:");
        embMsg.setTitle(track.getInfo().title, track.getInfo().uri);
        embMsg.addField("Duration", VoiceUtils.getTimestamp(track.getDuration()), true);
        embMsg.addField("Author", track.getInfo().author, true);

        EmbedMessageUtils.setColorByProvider(embMsg, track.getInfo().uri);

        return embMsg;
    }

    @NotNull
    public static EmbedBuilder getMusicQueueMessage(@NotNull Queue<AudioTrack> queue) {
        int trackCount = 0;
        long queueLength = 0;
        EmbedBuilder embMsg = new EmbedBuilder();
        StringBuilder songs = new StringBuilder();

        embMsg.setAuthor("Current Queue:");
        embMsg.addField("Entries", Integer.toString(queue.size()), true);

        for (AudioTrack track : queue) {
            queueLength += track.getDuration();
            if (trackCount < 10) {
                songs.append("`[")
                        .append(getTimestamp(track.getDuration()))
                        .append("]` ")
                        .append(track.getInfo().title)
                        .append("\n");

                trackCount++;
            }
        }

        embMsg.setDescription(songs.toString());
        embMsg.addField("Total Queue Time Length", getTimestamp(queueLength), true);
        embMsg.setFooter("@DodoBot", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");

        return embMsg;
    }

    private static void setColorByProvider(@NotNull EmbedBuilder embMsg, @NotNull String uri) {
        if (uri.contains("youtube")) {
            embMsg.setFooter("@Youtube", "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fhome.lps.org%2Ftraining%2Ffiles%2F2015%2F02%2FYouTube-logo-play-icon-500.png&f=1&nofb=1");
            embMsg.setColor(new Color(229, 39, 45));
        } else if (uri.contains("twitch")) {
            embMsg.setFooter("@Twitch", "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fpress.neocoregames.com%2Fstorage%2Ftwitch_logo_nobackground.png&f=1&nofb=1");
            embMsg.setColor(new Color(100, 65, 165));
        } else if (uri.contains("soundcloud")) {
            embMsg.setFooter("@SoundCloud", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flh3.googleusercontent.com%2FZwkhZQkwDp8zN6s3HqIYjRMBio3hQhSZczBga6XGnp_DseFgBF6R4XCco49MpdFTdUVz%3Dw170&f=1&nofb=1");
            embMsg.setColor(new Color(255, 127, 52));
        } else if (uri.contains("vimeo")) {
            embMsg.setFooter("@Vimeo", "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fgrowhealthy.rutgers.edu%2Fimages%2Fvimeo-logo-30x30.png&f=1&nofb=1");
            embMsg.setColor(new Color(25, 183, 234));
        } else {
            embMsg.setFooter("@Local", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg");
            embMsg.setColor(new Color(229, 12, 212));
        }
    }
}
