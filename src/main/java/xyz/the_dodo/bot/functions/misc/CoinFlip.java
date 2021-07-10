package xyz.the_dodo.bot.functions.misc;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.RandomGen;

@BotService(command = "coinFlip", description = "Flips a coin", usage = "coinFlip")
public class CoinFlip extends IFunction {
    public CoinFlip(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        this.responseQueue.add(new BotResponse(BotResponseTypeEnum.TEXT, RandomGen.rndNm(2) == 0 ? "Tails" : "Heads", messageParams.getTextChannel()));

        return this;
    }
}
