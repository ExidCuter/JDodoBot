package xyz.the_dodo.bot.types.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.User;
import xyz.the_dodo.bot.exceptions.StageNotCompletedException;
import xyz.the_dodo.bot.functions.IMultipleStage;

@Getter
@Setter
@Builder
public class Stage {
    private User user;
    private IMultipleStage command;
    private MessageParams orgMessage;
    private StageStatus status;

    public void executeNext(MessageParams messageParams) throws StageNotCompletedException {
        this.command.stage(messageParams, this.orgMessage).trigger();
    }

    public void handleError(StageNotCompletedException e) {
        this.status = StageStatus.INTERRUPTED;
        this.command.handleStageError(e, this.orgMessage).trigger();
    }
}
