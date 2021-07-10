package xyz.the_dodo.bot.utils;

import xyz.the_dodo.bot.types.message.CommandCategoryEnum;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static String[] getCommandAndParameters(String str) {
        return str.split(" ");
    }

    public static String[] getParameters(String str) {
        String[] commandsNParameters = getCommandAndParameters(str);
        String[] parameters = new String[commandsNParameters.length - 1];

        System.arraycopy(commandsNParameters, 1, parameters, 0, commandsNParameters.length - 1);

        return parameters;
    }

    public static String glueStringsBackTogether(String tab[], String between, int startIndex) {
        StringBuilder gluedString = new StringBuilder();

        for (int i = startIndex; i < tab.length; i++) {
            gluedString.append(tab[i]);

            if (i < tab.length - 1) {
                gluedString.append(between);
            }
        }

        return gluedString.toString();
    }

    public static List<String> splitIntoMessages(String str, char splitOn) {
        ArrayList<String> out;
        int brakePoint, lastBrakePoint;

        out = new ArrayList<>();

        if (str.length() < 1900) {
            out.add(str);

            return out;
        }

        brakePoint = 0;
        lastBrakePoint = str.length();

        for (int i = str.length() - 1; i > -1; i--) {
            if (i % 1900 == 0) {
                out.add(str.substring(brakePoint, lastBrakePoint));
                lastBrakePoint = brakePoint;
            }

            if (str.charAt(i) == splitOn) {
                brakePoint = i;
            }
        }

        return out;
    }

    public static String capitaliseFirsLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static boolean containsCategoryEnum(String value) {
        if (value.equalsIgnoreCase(CommandCategoryEnum.ADMIN.toString()))
            return false;

        for (CommandCategoryEnum c : CommandCategoryEnum.values()) {
            if (c.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
