package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.AdminUtils;
import xyz.the_dodo.bot.utils.RulesUtils;
import xyz.the_dodo.bot.utils.ServerUtils;
import xyz.the_dodo.database.types.Rules;
import xyz.the_dodo.database.types.Server;

@BotService(command = "setRules", description = "Sets the guild rules!", usage = "setRules <RULES TEXT>", category = CommandCategoryEnum.UTILS)
public class SetRules extends IFunction {
    public SetRules(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        Rules rules;
        Server server;

        if (AdminUtils.isAdminOfGuild(messageParams.getUser(), messageParams.getGuild())) {
            if (messageParams.getParameters().length > 0) {
                if (RulesUtils.rulesExist(messageParams.getGuild())) {
                    rules = RulesUtils.findByGuild(messageParams.getGuild());

                } else {
                    if (!ServerUtils.serverExist(messageParams.getGuild()))
                        ServerUtils.createServer(messageParams.getGuild());

                    server = ServerUtils.serverService.findByDiscordId(messageParams.getGuild().getId());

                    rules = new Rules();

                    rules.setServer(server);
                }

                rules.setRules(messageParams.getContent());

                RulesUtils.saveRules(rules);

                messageParams.getTextChannel().sendMessage("Rules updated!").queue();

            } else
                messageParams.getTextChannel().sendMessage("Please specify the rules").queue();

        } else
            messageParams.getTextChannel().sendMessage("Only admins can change the Guild Rules!").queue();
    }
}
