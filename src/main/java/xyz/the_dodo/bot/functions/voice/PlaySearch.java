package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import org.td_fl.youtube.YoutubeSearch;
import org.td_fl.youtube.models.Video;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.exceptions.StageNotCompletedException;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.functions.IMultipleStage;
import xyz.the_dodo.bot.functions.misc.YTSearch;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.types.audio.TrackScheduler;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

import java.io.IOException;
import java.util.List;

@BotService(command = "playSearch", description = "Plays a song from YouTube Search", usage = "play || play <SONG LINK>", category = CommandCategoryEnum.VOICE)
public class PlaySearch extends IFunction implements IMultipleStage {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);
    private YTSearch ytSearch;

    public PlaySearch(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
        ytSearch = new YTSearch(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        ytSearch.prepare(messageParams).trigger();

        this.responseQueue.add(BotResponse.builder()
                .response("Enter the number you want.")
                .responseType(BotResponseTypeEnum.TEXT)
                .textChannel(messageParams.getTextChannel())
                .build()
        );

        return this;
    }

    @Override
    public IFunction stage(MessageParams latest, MessageParams org) throws StageNotCompletedException {
        GuildMusicManager musicManager = voiceUtils.getMusicManager(latest.getGuild());

        try {
            int i = Integer.parseInt(latest.getCommand());
            List<Video> videos = YoutubeSearch.search(org.getContent());

            if (i > 8 || i < 0 || i > videos.size()) {
                throw new StageNotCompletedException("Error! Provided number is not on the list!");
            }

            voiceUtils.loadAndPlay(musicManager, latest.getMessage().getChannel(), videos.get(i - 1).getLink(), false, latest.getMessage().getMember(), videos.get(i - 1));

        } catch (NumberFormatException e) {
            throw new StageNotCompletedException("Error! Provided answer was not a number!");
        } catch (Exception e) {
            throw new StageNotCompletedException("Error occurred while searching!");
        }

        return this;
    }

    @Override
    public IFunction handleStageError(StageNotCompletedException e, MessageParams org) {
        this.responseQueue.add(BotResponse.builder()
                .response(e.getMessage())
                .responseType(BotResponseTypeEnum.TEXT)
                .textChannel(org.getTextChannel())
                .build()
        );

        return this;
    }
}
