package xyz.the_dodo.bot.functions;

import xyz.the_dodo.bot.exceptions.StageNotCompletedException;
import xyz.the_dodo.bot.types.message.MessageParams;

public interface IMultipleStage {
    IFunction stage(MessageParams latest, MessageParams org) throws StageNotCompletedException;
    IFunction handleStageError(StageNotCompletedException e, MessageParams org);
}
