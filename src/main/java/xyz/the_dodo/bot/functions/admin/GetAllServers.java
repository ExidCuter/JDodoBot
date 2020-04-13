package xyz.the_dodo.bot.functions.admin;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.StringUtils;

@BotService(command = "getServers", category = CommandCategoryEnum.ADMIN)
public class GetAllServers extends IFunction {
    public GetAllServers(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        StringBuilder stringBuilder;

        stringBuilder = new StringBuilder();

        if (AdminUtils.isUserBotOwner(messageParams.getUser())) {
            config.getGuilds().forEach(guild ->
                    stringBuilder.append(guild.getName())
                            .append(" | ")
                            .append(guild.getOwner().getUser())
                            .append(" | ")
                            .append(guild.getRegion())
                            .append(" | `")
                            .append(guild.getId())
                            .append("`\n"));

            StringUtils.splitIntoMessages(stringBuilder.toString(), '\n').forEach(message -> messageParams.getTextChannel().sendMessage(message).queue());
        } else
            messageParams.getTextChannel().sendMessage("Only the bot owner can use this command!").queue();
    }
}
