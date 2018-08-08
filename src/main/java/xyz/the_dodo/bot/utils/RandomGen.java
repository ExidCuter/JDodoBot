package xyz.the_dodo.bot.utils;

import java.util.Random;

public class RandomGen {
	private static int prev = -1;

	public static int rndNm(int max) {
		Random rand = new Random();
		int value = rand.nextInt(max);
		while (value == prev) {
			value = rand.nextInt(max);
		}
		return value;
	}
	public static int rndNm(int min, int max) {
		Random rand = new Random();
		int value = rand.nextInt(max);
		while (value < min) {
			value = rand.nextInt(max);
		}
		return value;
	}
}
