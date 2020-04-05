package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;

@BotService(command = "roll", description = "rolls a X sided dice", usage = "roll <MAX>")
public class Roll extends IFunction {
    public Roll(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        int value, max;
        MessageChannel channel;

        channel = messageParams.getTextChannel();

        if (messageParams.getParameters() == null || messageParams.getParameters().length == 0) {
            value = RandomGen.rndNm(7);

            while (value == 0)
                value = RandomGen.rndNm(7);

            channel.sendMessage(String.valueOf(value)).queue();
        } else if (messageParams.getParameters().length > 0) {
            max = Integer.parseInt(messageParams.getParameters()[0]) + 1;
            value = RandomGen.rndNm(max);

            while (value == 0)
                value = RandomGen.rndNm(max);

            channel.sendMessage(String.valueOf(value)).queue();
        }
    }
}
