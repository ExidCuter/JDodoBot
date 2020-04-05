package xyz.the_dodo.bot.types;

import lombok.Getter;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.utils.StringUtils;

@Getter
public class MessageParams {
    private User user;
    private Guild guild;
    private String content;
    private String command;
    private Message message;
    private String[] parameters;
    private TextChannel textChannel;


    public MessageParams(String command, User user, Guild guild, TextChannel textChannel) {
        this.user = user;
        this.guild = guild;
        this.textChannel = textChannel;
        parameters = StringUtils.getParameters(command);
        this.command = StringUtils.getCommandNParameters(command)[0];
        content = StringUtils.glueStringsBackTogether(parameters, " ", 0);
        message = new DefaultMessage(parameters, guild);
    }

    public MessageParams(Message message) {
        String rawContent;

        rawContent = message.getContentRaw();

        this.message = message;
        this.user = message.getAuthor();
        this.guild = message.getGuild();
        this.textChannel = message.getTextChannel();
        this.parameters = StringUtils.getParameters(rawContent);
        this.command = StringUtils.getCommandNParameters(rawContent)[0];
        this.content = StringUtils.glueStringsBackTogether(parameters, " ", 0);
    }
}
