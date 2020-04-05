package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.TrackScheduler;

@BotService(command = "play", description = "Plays a song or resumes playing", usage = "play || play <SONG LINK>", category = CommandCategoryEnum.VOICE)
public class Play extends IFunction {
    public Play(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Guild guild;
        AudioPlayer player;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        player = musicManager.player;
        scheduler = musicManager.scheduler;

        if (messageParams.getParameters().length > 0) //Commands has 2 parts, .play and url.
            DodoBot.getVoiceUtils().loadAndPlay(musicManager, messageParams.getMessage().getChannel(), messageParams.getParameters()[0], false);
        else { //It is only the command to start playback (probably after pause)
            if (player.isPaused()) {
                player.setPaused(false);

                messageParams.getTextChannel().sendMessage("Playback as been resumed.").queue();
            } else if (player.getPlayingTrack() != null)
                messageParams.getTextChannel().sendMessage("Player is already playing!").queue();
            else if (scheduler.isEmpty())
                messageParams.getTextChannel().sendMessage("The current audio queue is empty! Add something to the queue first!").queue();
        }
    }
}
