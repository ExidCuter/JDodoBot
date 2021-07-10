package xyz.the_dodo.bot.functions.stats;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.Stats;

import java.util.List;
import java.util.stream.Collectors;

@BotService(command = "topStats", description = "Shows the server \"stats\" leaderboard!", usage = "!topStats", category = CommandCategoryEnum.STATS)
public class TopStats extends IFunction {
    public TopStats(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        StringBuilder stringBuilder;
        List<Stats> currentServerStats;

        stringBuilder = new StringBuilder();

        currentServerStats = StatsUtils.statsService.findAll()
                .stream()
                .filter(stats -> messageParams.getGuild().getMemberById(stats.getUser().getDiscordId()) != null)
                .sorted((s1, s2) -> s2.getNumOfMessages().compareTo(s1.getNumOfMessages()))
                .collect(Collectors.toList());

        currentServerStats.forEach(stats ->
                stringBuilder.append(messageParams.getGuild().getMemberById(stats.getUser().getDiscordId()).getAsMention())
                        .append(": ")
                        .append(stats.getNumOfMessages())
                        .append("\n"));

        StringUtils.splitIntoMessages(stringBuilder.toString(), '\n')
                .forEach(message -> messageParams.getTextChannel().sendMessage(message).queue());

        return this;
    }
}
