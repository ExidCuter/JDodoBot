package xyz.the_dodo.bot.types.message;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
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
        this.parameters = StringUtils.getParameters(command);
        this.message = new DefaultMessage(parameters, guild);
        this.command = StringUtils.getCommandAndParameters(command)[0];
        this.content = StringUtils.glueStringsBackTogether(parameters, " ", 0);
    }

    public MessageParams(@NotNull Message message) {
        String rawContent = message.getContentRaw();

        this.message = message;
        this.user = message.getAuthor();

        try {
            this.guild = message.getGuild();
            this.textChannel = message.getTextChannel();
        } catch (Exception e) {
            this.guild = null;
            this.textChannel = null;
        }

        this.parameters = StringUtils.getParameters(rawContent);
        this.command = StringUtils.getCommandAndParameters(rawContent)[0];
        this.content = StringUtils.glueStringsBackTogether(parameters, " ", 0);
    }
}
