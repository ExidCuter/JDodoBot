package xyz.the_dodo.bot.types.response;

import lombok.*;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.io.ByteArrayOutputStream;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotResponse {
    private BotResponseTypeEnum responseType;
    private Object response;
    private TextChannel textChannel;

    public MessageAction generateMessage() {
        if (responseType == BotResponseTypeEnum.TEXT) {
            return textChannel.sendMessage((String) response);
        } else if (responseType == BotResponseTypeEnum.FILE_PNG) {
            return textChannel.sendFile(((ByteArrayOutputStream) response).toByteArray(), "file.png");
        } else {
            return textChannel.sendMessage((MessageEmbed) response);
        }
    }
}
