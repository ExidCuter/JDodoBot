package xyz.the_dodo.bot.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
	public static String[] getCommandNParameters(String str){
		return str.split(" ");
	}

	public static String[] getParameters(String str) {
		String[] commandsNParameters = getCommandNParameters(str);
		String[] parameters = new String[commandsNParameters.length-1];
		for (int i = 1; i<commandsNParameters.length; i++) {
			parameters[i-1]=commandsNParameters[i];
		}
		return parameters;
	}

	public static String glueStringsBackTogether(String tab[], String between, int startIndex){
		StringBuilder gluedString = new StringBuilder();
		for (int i = startIndex; i < tab.length; i++){
			gluedString.append(tab[i]);
			if (i<tab.length-1)
				gluedString.append(between);
		}
		return gluedString.toString();
	}

	public static List<String> splitIntoMessages(String str, char splitOn) {
		int j = 0;
		ArrayList<String> out = new ArrayList<>();
		int brakePoint = 0;
		int lastBrakePoint = str.length();
		for (int i = str.length() - 1; i > -1; i--) {
			if(i%1900 == 0){
				out.add(str.substring(brakePoint,lastBrakePoint));
				lastBrakePoint = brakePoint;
			}
			if(str.charAt(i) == splitOn) {
				brakePoint = i;
			}
		}
		return out;
	}
}
