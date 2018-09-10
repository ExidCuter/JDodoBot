package xyz.the_dodo.bot.Functions.stats;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.bot.utils.UserUtils;
import xyz.the_dodo.database.types.Stats;
import xyz.the_dodo.database.types.User;

public class CountStats extends IFunction {
    public CountStats(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.STATS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        User user;
        Stats stats;

        stats = StatsUtils.statsExists(p_messageParams.getUser());

        if (stats == null) {
            stats = new Stats();

            if (!UserUtils.userExists(p_messageParams.getUser()))
                UserUtils.createDodoUser(p_messageParams.getUser());

            user = UserUtils.m_userService.findByDiscordId(p_messageParams.getUser().getId());

            stats.setUser(user);
            stats.setNumOfFiles(0L);
            stats.setNumOfMessages(1L);

            StatsUtils.saveStats(stats);

            p_messageParams.getMessage().addReaction("\u2705").queue();
            p_messageParams.getTextChannel().sendMessage("Use `!stats` to check your stats").queue();
        }
        else
            p_messageParams.getTextChannel().sendMessage("Your stats are already being tracked!").queue();
    }
}
