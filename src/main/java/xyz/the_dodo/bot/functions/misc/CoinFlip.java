package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.TextChannel;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;

@BotService(command = "coinFlip", description = "Flips a coin", usage = "coinFlip")
public class CoinFlip extends IFunction {
    public CoinFlip(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        TextChannel textChannel = messageParams.getTextChannel();
        int x = RandomGen.rndNm(2);
        if (x == 0)
            textChannel.sendMessage("Tails").queue();
        else
            textChannel.sendMessage("Heads").queue();
    }
}
