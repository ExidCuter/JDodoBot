package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.BannedUtils;
import xyz.the_dodo.bot.utils.StringUtils;

@BotService(command = "getBannedUsers", description = "Gets the list of all the banned user", usage = "getBannedUsers", category = CommandCategoryEnum.UTILS)
public class GetBannedUsers extends IFunction {
    public GetBannedUsers(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        StringBuilder builder;

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            builder = new StringBuilder();

            builder.append("Banned users:");

            BannedUtils.bannedService.findByServerDiscordId(messageParams.getGuild().getId()).forEach(bannedUser -> builder.append(messageParams.getGuild().getMemberById(bannedUser.getUser().getDiscordId())).append("\n"));

            StringUtils.splitIntoMessages(builder.toString(), '\n').forEach(message -> messageParams.getTextChannel().sendMessage(message).queue());
        }

        return this;
    }
}
