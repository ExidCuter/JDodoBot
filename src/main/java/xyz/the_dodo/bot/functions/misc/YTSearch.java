package xyz.the_dodo.bot.functions.misc;

import org.td_fl.youtube.YoutubeSearch;
import org.td_fl.youtube.models.Video;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.EmbedMessageUtils;

import java.util.List;

@BotService(command = "search", description = "Searches for YouTube videos!", usage = "search <QUERY>")
public class YTSearch extends IFunction {
    public YTSearch(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        if (!messageParams.getContent().equals("")) {
            try {
                List<Video> videos = YoutubeSearch.search(messageParams.getContent());

                this.responseQueue.add(BotResponse.builder()
                        .responseType(BotResponseTypeEnum.EMBED)
                        .response(EmbedMessageUtils.getYoutubeSearchMessage(videos, messageParams.getContent()).build())
                        .textChannel(messageParams.getTextChannel())
                        .build()
                );

            } catch (Exception e) {
                this.responseQueue.add(BotResponse.builder()
                        .responseType(BotResponseTypeEnum.TEXT)
                        .response("An error occurred while searching!")
                        .textChannel(messageParams.getTextChannel())
                        .build()
                );
            }
        }

        return this;
    }
}
