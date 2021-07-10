package xyz.the_dodo.bot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.the_dodo.bot.exceptions.StageNotCompletedException;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.types.message.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageListener extends ListenerAdapter {
    private static final Map<String, Stage> userStage = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isFake()) {
            return;
        }

        Stage stage = userStage.get(event.getAuthor().getId());

        if (stage != null) {
            try {
                stage.executeNext(new MessageParams(event.getMessage()));
            } catch (StageNotCompletedException e) {
                stage.handleError(e);
            }

            userStage.remove(event.getAuthor().getId());
        }
    }

    public static void registerUserStage(Stage stage) {
        userStage.put(stage.getUser().getId(), stage);
    }
}
