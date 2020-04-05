package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.CommandHandler;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.StringUtils;

@BotService(command = "help", description = "Shows help", usage = "help <#COMMAND>", category = CommandCategoryEnum.UTILS)
public class Help extends IFunction {
    public Help(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        if (messageParams.getParameters().length > 0) {
            if (StringUtils.containsCategoryEnum(messageParams.getParameters()[0].toUpperCase()))
                messageParams.getUser()
                        .openPrivateChannel()
                        .queue(p_privateChannel ->
                                p_privateChannel.sendMessage(CommandHandler.generateHelp(messageParams.getParameters()[0])).queue());
            else
                CommandHandler.commands.values().stream()
                        .filter(command -> command.getCommand().equals(messageParams.getParameters()[0]))
                        .forEach(command ->
                                messageParams.getUser()
                                        .openPrivateChannel()
                                        .queue(p_privateChannel -> p_privateChannel.sendMessage(command.getEmbededHelp().build()).queue()));
        } else
            messageParams.getUser().openPrivateChannel().queue(p_privateChannel -> p_privateChannel.sendMessage(CommandHandler.generateHelp()).queue());
    }
}
