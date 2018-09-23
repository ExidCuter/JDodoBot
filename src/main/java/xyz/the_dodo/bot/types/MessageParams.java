package xyz.the_dodo.bot.types;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.utils.StringUtils;

public class MessageParams {
    private User user;
    private Guild guild;
    private String content;
    private String command;
    private Message message;
    private String[] parameters;
    private TextChannel textChannel;

    public User getUser() {
        return user;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getContent() {
        return content;
    }

    public String getCommand() {
        return command;
    }

    public Message getMessage() {
        return message;
    }

    public String[] getParameters() {
        return parameters;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public MessageParams(String p_command, User p_author, Guild p_guild, TextChannel p_textChannel) {
        user = p_author;
        guild = p_guild;
        textChannel = p_textChannel;
        parameters = StringUtils.getParameters(p_command);
        command = StringUtils.getCommandNParameters(p_command)[0];
        content = StringUtils.glueStringsBackTogether(parameters, " ", 0);
        message = new DefaultMessage(parameters, p_guild);
    }

    public MessageParams(Message p_message) {
        String rawContent;

        rawContent = p_message.getContentRaw();

        message = p_message;
        user = p_message.getAuthor();
        guild = p_message.getGuild();
        textChannel = p_message.getTextChannel();
        parameters = StringUtils.getParameters(rawContent);
        command = StringUtils.getCommandNParameters(rawContent)[0];
        content = StringUtils.glueStringsBackTogether(parameters, " ", 0);
    }
}
