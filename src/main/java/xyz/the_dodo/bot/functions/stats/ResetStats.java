package xyz.the_dodo.bot.functions.stats;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.Stats;
import xyz.the_dodo.database.types.User;

public class ResetStats extends IFunction {
    public ResetStats(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.STATS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Stats stats;

        stats = StatsUtils.statsExists(p_messageParams.getUser());

        if (stats != null) {

            StatsUtils.m_statsService.delete(stats);

            p_messageParams.getMessage().addReaction("\u2705").queue();
            p_messageParams.getTextChannel().sendMessage("Your stats are not being tracked anymore!").queue();
        }
        else
            p_messageParams.getTextChannel().sendMessage("Your stats are not being tracked!").queue();
    }
}
