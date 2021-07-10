package xyz.the_dodo.bot.functions.misc;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;

@BotService(command = "hi", description = "Says helo", usage = "hi")
public class Hi extends IFunction {
    public Hi(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        this.responseQueue.add(new BotResponse(BotResponseTypeEnum.TEXT, "Hi, " + messageParams.getUser().getAsMention(), messageParams.getTextChannel()));

        return this;
    }
}
