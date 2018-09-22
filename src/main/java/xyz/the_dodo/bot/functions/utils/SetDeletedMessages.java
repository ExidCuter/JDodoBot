package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.Server;

public class SetDeletedMessages extends IFunction {
    public SetDeletedMessages(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Server server;

        if(p_messageParams.getMessage().getMember().isOwner() || AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            if (p_messageParams.getParameters().length > 0) {
                if (!ServerUtils.serverExist(p_messageParams.getGuild())) {
                    ServerUtils.createServer(p_messageParams.getGuild());
                }

                server = ServerUtils.m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

                if (p_messageParams.getParameters()[0].equalsIgnoreCase("true")) {
                    server.setSaveDeleted(true);
                    p_messageParams.getTextChannel().sendMessage("Bot will now keep track of deleted posts!").queue();
                } else if (p_messageParams.getParameters()[0].equalsIgnoreCase("false")) {
                    server.setSaveDeleted(false);
                    p_messageParams.getTextChannel().sendMessage("Bot will now stop tracking deleted posts!").queue();
                }

                ServerUtils.m_serverService.save(server);
            } else
                p_messageParams.getTextChannel().sendMessage(getEmbededHelp().build()).queue();
        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can use this command!").queue();
    }
}
