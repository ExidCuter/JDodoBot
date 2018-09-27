package xyz.the_dodo.bot.functions.admin;

import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

public class GetAllServers extends IFunction {
    public GetAllServers(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.ADMIN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
         StringBuilder stringBuilder;

         stringBuilder = new StringBuilder();

         if (p_messageParams.getUser().getId().equals(DodoBot.botOwner)) {
             DodoBot.getGuilds().forEach(p_guild ->
                     stringBuilder.append(p_guild.getName())
                             .append(" | ")
                             .append(p_guild.getOwner().getUser())
                             .append(" | ")
                             .append(p_guild.getRegion())
                             .append(" | `")
                             .append(p_guild.getId())
                             .append("`\n"));

             StringUtils.splitIntoMessages(stringBuilder.toString(), '\n').forEach(p_message -> p_messageParams.getTextChannel().sendMessage(p_message).queue());
         } else
             p_messageParams.getTextChannel().sendMessage("Only the bot owner can use this command!").queue();
    }
}
