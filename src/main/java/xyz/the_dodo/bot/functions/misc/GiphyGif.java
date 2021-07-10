package xyz.the_dodo.bot.functions.misc;

import com.kdotj.simplegiphy.SimpleGiphy;
import com.kdotj.simplegiphy.data.Giphy;
import com.kdotj.simplegiphy.data.GiphyListResponse;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.bot.utils.StringUtils;

import java.util.List;

@BotService(command = "gif", description = "Gets a GIF from Giphy", usage = "gif <QUERY>")
public class GiphyGif extends IFunction {
    public GiphyGif(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        String query;
        List<Giphy> data;
        GiphyListResponse trendingResponse;

        query = StringUtils.glueStringsBackTogether(messageParams.getParameters(), " ", 0);

        if (query.length() > 0) {
            trendingResponse = SimpleGiphy.getInstance().search(query, "5", "0", "");
            data = trendingResponse.getData();

            if (!data.isEmpty()) {
                messageParams.getTextChannel().sendMessage(data.get(RandomGen.rndNm(data.size())).getUrl()).queue();
            } else {
                messageParams.getTextChannel().sendMessage("No GIFs found!").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage(this.getEmbededHelp().build()).queue();
        }

        return this;
    }
}
