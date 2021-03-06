package xyz.the_dodo.bot.functions.voice;

import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;

@BotService(command = "leave", description = "Leaves voice channel", usage = "leave", category = CommandCategoryEnum.VOICE)
public class Leave extends IFunction {
    public Leave(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        messageParams.getGuild().getAudioManager().setSendingHandler(null);
        messageParams.getGuild().getAudioManager().closeAudioConnection();

        return this;
    }
}
