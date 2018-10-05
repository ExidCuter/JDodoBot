package xyz.the_dodo.bot.functions.utils;

import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.types.CommandHandler;
import xyz.the_dodo.bot.utils.StringUtils;

public class Help extends IFunction {
    public Help(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.UTILS;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (p_messageParams.getParameters().length > 0) {
            if (StringUtils.containsCategoryEnum(p_messageParams.getParameters()[0].toUpperCase()))
                p_messageParams.getTextChannel().sendMessage(CommandHandler.generateHelp(p_messageParams.getParameters()[0])).queue();
            else
                CommandHandler.commands.stream()
                        .filter(command -> command.getCommand().equals(p_messageParams.getParameters()[0]))
                        .forEach(command -> p_messageParams.getTextChannel().sendMessage(command.getEmbededHelp().build()).queue());
        }
        else
            p_messageParams.getTextChannel().sendMessage(CommandHandler.generateHelp()).queue();
    }
}
