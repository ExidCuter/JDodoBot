package xyz.the_dodo.bot.functions.stats;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StatsUtils;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.database.types.Stats;

import java.util.List;
import java.util.stream.Collectors;

public class TopStats extends IFunction {
    public TopStats(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.STATS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        StringBuilder stringBuilder;
        List<Stats> currentServerStats;

        stringBuilder = new StringBuilder();

        currentServerStats = StatsUtils.m_statsService.findAll()
                .stream()
                .filter(stats -> p_messageParams.getGuild().getMemberById(stats.getUser().getDiscordId()) != null)
                .sorted((s1, s2) -> s2.getNumOfMessages().compareTo(s1.getNumOfMessages()))
                .collect(Collectors.toList());

        currentServerStats.forEach(stats ->
                stringBuilder.append(p_messageParams.getGuild().getMemberById(stats.getUser().getDiscordId()).getAsMention())
                        .append(": ")
                        .append(stats.getNumOfMessages())
                        .append("\n"));

        StringUtils.splitIntoMessages(stringBuilder.toString(), '\n')
                .forEach(message -> p_messageParams.getTextChannel().sendMessage(message).queue());

    }
}
