package xyz.the_dodo.bot.Functions.misc;

import com.kdotj.simplegiphy.SimpleGiphy;
import com.kdotj.simplegiphy.data.Giphy;
import com.kdotj.simplegiphy.data.GiphyListResponse;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;
import xyz.the_dodo.bot.utils.StringUtils;

import java.util.List;

public class GiphyGif extends IFunction {
    public GiphyGif(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        String query;
        List<Giphy> data;
        GiphyListResponse trendingResponse;

        query = StringUtils.glueStringsBackTogether(p_messageParams.getParameters(), " ", 0);

        if (query.length() > 0) {
            trendingResponse = SimpleGiphy.getInstance().search(query, "5", "0", "");
            data = trendingResponse.getData();

            if (!data.isEmpty()) {
                p_messageParams.getTextChannel().sendMessage(data.get(RandomGen.rndNm(data.size())).getUrl()).queue();
            } else {
                p_messageParams.getTextChannel().sendMessage("No GIFs found!").queue();
            }
        } else {
            p_messageParams.getTextChannel().sendMessage(this.getEmbededHelp().build()).queue();
        }
    }
}
