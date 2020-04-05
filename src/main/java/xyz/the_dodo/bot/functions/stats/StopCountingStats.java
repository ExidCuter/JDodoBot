package xyz.the_dodo.bot.functions.stats;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.database.types.Stats;

@BotService(command = "stopStats", description = "Stops tracking your stats", usage = "stopStats", category = CommandCategoryEnum.STATS)
public class StopCountingStats extends IFunction {
    public StopCountingStats(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Stats stats;

        stats = StatsUtils.statsExists(messageParams.getUser());

        if (stats != null) {

            StatsUtils.m_statsService.delete(stats);

            messageParams.getMessage().addReaction("\u2705").queue();
            messageParams.getTextChannel().sendMessage("Your stats are not being tracked anymore!").queue();
        } else
            messageParams.getTextChannel().sendMessage("Your stats are not being tracked!").queue();
    }
}
