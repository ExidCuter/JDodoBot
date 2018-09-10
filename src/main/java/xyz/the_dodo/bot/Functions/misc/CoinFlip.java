package xyz.the_dodo.bot.Functions.misc;

import net.dv8tion.jda.core.entities.TextChannel;
import xyz.the_dodo.bot.Functions.IFunction;
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
        try {
            int x = RandomGen.rndNm(2);
            if (x == 0)
                textChannel.sendMessage("Tails").complete();
            else
                textChannel.sendMessage("Heads").complete();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: bug reporting
        }
    }
}
