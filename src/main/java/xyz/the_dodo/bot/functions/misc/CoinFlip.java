package xyz.the_dodo.bot.functions.misc;

import net.dv8tion.jda.core.entities.TextChannel;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;

public class CoinFlip extends IFunction {

    public CoinFlip(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.FUN;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        TextChannel textChannel = p_messageParams.getTextChannel();
        int x = RandomGen.rndNm(2);
        if (x == 0)
            textChannel.sendMessage("Tails").queue();
        else
            textChannel.sendMessage("Heads").queue();
    }
}
