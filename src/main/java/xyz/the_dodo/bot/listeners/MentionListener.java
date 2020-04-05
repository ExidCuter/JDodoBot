package xyz.the_dodo.bot.listeners;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.utils.StringUtils;

public class MentionListener extends ListenerAdapter {
    public static String CLEVERBOT_API_KEY;
    private static ChatterBotFactory factory = new ChatterBotFactory();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message;
        String parameters[];

        if (event.getAuthor().isBot())
            return;

        message = event.getMessage();
        parameters = StringUtils.getCommandNParameters(message.getContentRaw());

        if (!message.getMentionedUsers().isEmpty() && message.getMentionedUsers().get(0).getName().equals(DodoBot.getName()) && parameters.length > 1) {
            String statement = StringUtils.glueStringsBackTogether(parameters, " ", 1);
            try {
                ChatterBot bot = factory.create(ChatterBotType.CLEVERBOT, CLEVERBOT_API_KEY);
                ChatterBotSession botSession = bot.createSession();

                message.getTextChannel().sendMessage(botSession.think(statement)).queue();

            } catch (Exception e) {
                message.getTextChannel().sendMessage("An error occurred!").queue();
            }
        }
    }
}
