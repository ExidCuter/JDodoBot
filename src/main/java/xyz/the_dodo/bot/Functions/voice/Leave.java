package xyz.the_dodo.bot.Functions.voice;

import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;

public class Leave extends IFunction {
    public Leave(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        p_messageParams.getGuild().getAudioManager().setSendingHandler(null);
        p_messageParams.getGuild().getAudioManager().closeAudioConnection();
    }
}
