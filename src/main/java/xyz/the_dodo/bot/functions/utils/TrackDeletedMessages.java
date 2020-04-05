package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.Server;

@BotService(command = "trackDeleted", description = "Tracks deleted messages of this guild!", usage = "trackDeleted <TRUE/FALSE>", category = CommandCategoryEnum.UTILS)
public class TrackDeletedMessages extends IFunction {
    public TrackDeletedMessages(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Server server;

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            if (messageParams.getParameters().length > 0) {
                if (!ServerUtils.serverExist(messageParams.getGuild())) {
                    ServerUtils.createServer(messageParams.getGuild());
                }

                server = ServerUtils.m_serverService.findByDiscordId(messageParams.getGuild().getId());

                if (messageParams.getParameters()[0].equalsIgnoreCase("true")) {
                    server.setSaveDeleted(true);
                    messageParams.getTextChannel().sendMessage("Bot will now keep track of deleted posts!").queue();
                } else if (messageParams.getParameters()[0].equalsIgnoreCase("false")) {
                    server.setSaveDeleted(false);
                    messageParams.getTextChannel().sendMessage("Bot will now stop tracking deleted posts!").queue();
                }

                ServerUtils.m_serverService.save(server);
            } else
                messageParams.getTextChannel().sendMessage(getEmbededHelp().build()).queue();
        } else
            messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
    }
}
