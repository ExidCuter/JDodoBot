package xyz.the_dodo.bot.functions;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.core.EmbedBuilder;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;

import java.awt.*;

@Getter
@Setter
public abstract class IFunction {
    private String command;
    private String description;
    private String usage;
    private boolean isService;
    private CommandCategoryEnum commandCategoryEnum;

    public IFunction() {
    }

    public IFunction(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        this.command = command;
        this.description = description;
        this.usage = usage;
        this.isService = isService;
        this.commandCategoryEnum = commandCategoryEnum;
    }

    public abstract void trigger(MessageParams messageParams);

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
