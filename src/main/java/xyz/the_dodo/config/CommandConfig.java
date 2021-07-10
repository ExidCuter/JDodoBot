package xyz.the_dodo.config;

import org.reflections.Reflections;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandConfig {
    public static HashMap<String, IFunction> commands = new HashMap<>();

    public static void registerCommands() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Reflections ref = new Reflections("xyz.the_dodo.bot");
        for (Class<?> cl : ref.getTypesAnnotatedWith(BotService.class)) {
            BotService botService = cl.getAnnotation(BotService.class);

            commands.put(botService.command(), (IFunction) cl.getConstructor(String.class, String.class, String.class, boolean.class, CommandCategoryEnum.class).newInstance(botService.command(), botService.description(), botService.usage(), botService.isService(), botService.category()));
        }
    }

    public static String generateHelp() {
        StringBuilder builder;

        builder = new StringBuilder();

        builder.append("DodoBot function Categories:\n");

        for (CommandCategoryEnum c : CommandCategoryEnum.values()) {
            if (c.equals(CommandCategoryEnum.ADMIN))
                continue;
            builder.append("\t`" + c.toString() + "`\n");
        }

        builder.append("`type help <CATEGORY> to get more help`");
        return builder.toString();
    }

    public static String generateHelp(String category) {
        StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Commands from category `" + category + "`\n");

        CommandConfig.commands.values().stream()
                .filter(command -> command.getCommandCategoryEnum().toString().equalsIgnoreCase(category))
                .forEach(command -> builder.append("\t" + command.getHelp() + "\n"));

        builder.append("`type help <command> to get more help`");

        return builder.toString();
    }
}
