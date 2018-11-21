package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.RulesUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.Rules;
import xyz.the_dodo.database.types.Server;

public class SetRules extends IFunction {
    public SetRules(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Rules rules;
        Server server;

        if (AdminUtils.isAdminOfGuild(p_messageParams.getUser(), p_messageParams.getGuild())) {
            if (p_messageParams.getParameters().length > 0) {
                if (RulesUtils.rulesExist(p_messageParams.getGuild())) {
                    rules = RulesUtils.findByGuild(p_messageParams.getGuild());

                } else {
                    if (!ServerUtils.serverExist(p_messageParams.getGuild()))
                        ServerUtils.createServer(p_messageParams.getGuild());

                    server = ServerUtils.m_serverService.findByDiscordId(p_messageParams.getGuild().getId());

                    rules = new Rules();

                    rules.setServer(server);
                }

                rules.setRules(p_messageParams.getContent());

                RulesUtils.saveRules(rules);

                p_messageParams.getTextChannel().sendMessage("Rules updated!").queue();

            } else
                p_messageParams.getTextChannel().sendMessage("Please specify the rules").queue();

        } else
            p_messageParams.getTextChannel().sendMessage("Only admins can change the Guild Rules!").queue();
    }
}
