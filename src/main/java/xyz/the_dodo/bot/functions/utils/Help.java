package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;
import xyz.the_dodo.config.CommandConfig;

@BotService(command = "help", description = "Shows help", usage = "help <#COMMAND>", category = CommandCategoryEnum.UTILS)
public class Help extends IFunction {
    public Help(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        if (messageParams.getParameters().length > 0) {
            if (StringUtils.containsCategoryEnum(messageParams.getParameters()[0].toUpperCase())) {
                messageParams.getUser()
                        .openPrivateChannel()
                        .queue(privateChannel ->
                                privateChannel.sendMessage(CommandConfig.generateHelp(messageParams.getParameters()[0])).queue());
            } else {
                CommandConfig.commands.values().stream()
                        .filter(command -> command.getCommand().equals(messageParams.getParameters()[0]))
                        .forEach(command ->
                                messageParams.getUser()
                                        .openPrivateChannel()
                                        .queue(privateChannel -> privateChannel.sendMessage(command.getEmbededHelp().build()).queue()));
            }
        } else {
            messageParams.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(CommandConfig.generateHelp()).queue());
        }

        return this;
    }
}
