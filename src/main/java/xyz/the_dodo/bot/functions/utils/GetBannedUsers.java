package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.BannedUtils;
import xyz.the_dodo.bot.utils.StringUtils;

public class GetBannedUsers extends IFunction {
    public GetBannedUsers(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        StringBuilder builder;

        if (p_messageParams.getMessage().getMember().isOwner() || AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            builder = new StringBuilder();

            builder.append("Banned users:");

            BannedUtils.m_bannedService.findByServerDiscordId(p_messageParams.getGuild().getId()).forEach(p_bannedUser ->
                    builder.append(p_messageParams.getGuild().getMemberById(p_bannedUser.getUser().getDiscordId()) + "\n"));

            StringUtils.splitIntoMessages(builder.toString(), '\n').forEach(p_s -> p_messageParams.getTextChannel().sendMessage(p_s).queue());
        }
    }
}
