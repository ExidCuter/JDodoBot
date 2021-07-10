package xyz.the_dodo.bot.functions;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.EmbedBuilder;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.config.BotConfig;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public abstract class IFunction {
    protected static BotConfig config = BeanUtils.getBean(BotConfig.class);

    private String command;
    private String description;
    private String usage;
    private boolean isService;
    private CommandCategoryEnum commandCategoryEnum;

    protected final Queue<BotResponse> responseQueue;

    public IFunction() {
        this.responseQueue = new LinkedList<>();
    }

    public IFunction(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        this.responseQueue = new LinkedList<>();
        this.command = command;
        this.description = description;
        this.usage = usage;
        this.isService = isService;
        this.commandCategoryEnum = commandCategoryEnum;
    }

    public abstract IFunction prepare(MessageParams messageParams);

    public void trigger() {
        this.responseQueue.forEach(botResponse -> botResponse.generateMessage().queue());
        this.responseQueue.clear();
    }

    public String getHelp() {
        return "`" + this.command + "` - " + this.description;
    }

    public EmbedBuilder getEmbededHelp() {
        return new EmbedBuilder().setTitle("Command: " + this.command + "", "http://the-dodo.xyz")
                .setDescription(this.description)
                .setColor(new Color(0x21FF00))
                .setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg")
                .setAuthor("DodoBot help", "http://the-dodo.xyz", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b7/The_Dodo_Logo.jpg/250px-The_Dodo_Logo.jpg")
                .addField("Usage: (if the command parameter has the `#` in it, the parameter is not required)", "`" + this.usage + "`", false);
    }
}
