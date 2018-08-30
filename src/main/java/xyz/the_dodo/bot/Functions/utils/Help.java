package xyz.the_dodo.bot.Functions.utils;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.CommandHandler;

public class Help extends IFunction {
    public Help(String command, String description, String usage) {
        super(command, description, usage);
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        if (p_messageParams.getParameters().length > 0) {
            CommandHandler.commands.stream()
                    .filter(command -> command.getCommand().equals(p_messageParams.getParameters()[0]))
                    .forEach(command -> p_messageParams.getTextChannel().sendMessage(command.getEmbededHelp().build()).queue());
        }
        else
            p_messageParams.getTextChannel().sendMessage(CommandHandler.generateHelp()).queue();
    }
}
