package xyz.the_dodo.bot.functions.stats;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.database.types.Stats;
import xyz.the_dodo.database.types.User;

@BotService(command = "countStats", description = "Starts tracking your stats", usage = "countStats", category = CommandCategoryEnum.STATS)
public class CountStats extends IFunction {
    public CountStats(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        User user;
        Stats stats;

        stats = StatsUtils.statsExists(messageParams.getUser());

        if (stats == null) {
            stats = new Stats();

            if (!UserUtils.userExists(messageParams.getUser()))
                UserUtils.createDodoUser(messageParams.getUser());

            user = UserUtils.userService.findByDiscordId(messageParams.getUser().getId());

            stats.setUser(user);
            stats.setNumOfFiles(0L);
            stats.setNumOfMessages(1L);

            StatsUtils.saveStats(stats);

            messageParams.getMessage().addReaction("\u2705").queue();
            messageParams.getTextChannel().sendMessage("Use `!stats` to check your stats").queue();
        } else
            messageParams.getTextChannel().sendMessage("Your stats are already being tracked!").queue();
    }
}
