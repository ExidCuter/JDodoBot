package xyz.the_dodo.bot.types;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

import java.util.concurrent.ThreadLocalRandom;

public class SlotMashina {
    public static String slots[] = {":tangerine:", ":cookie:", ":heart:", ":floppy_disk:", ":minidisc:", ":cd:", ":film_frames:"};

    public static void slotMashinas(double money, MessageChannel msgchan, BankAccount ba) {
        int slotMashina[][] = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        String endGame = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                slotMashina[i][j] = ThreadLocalRandom.current().nextInt(0, slots.length - 1);
            }
            if (i == 1) {
                endGame = endGame + slots[slotMashina[i][0]] + " " + slots[slotMashina[i][1]] + " " + slots[slotMashina[i][2]] + " :arrow_left:\n";
            } else {
                endGame = endGame + slots[slotMashina[i][0]] + " " + slots[slotMashina[i][1]] + " " + slots[slotMashina[i][2]] + "\n";
            }
        }
        msgchan.sendMessage(endGame).queue();
        String out;
        if (slotMashina[1][0] == slotMashina[1][1] && slotMashina[1][1] == slotMashina[1][2]) {
            money = money * 3;
            out = "Three the same. WOW! +3x!";
        } else if (slotMashina[1][0] == slotMashina[1][1] || slotMashina[1][1] == slotMashina[1][2] || slotMashina[1][0] == slotMashina[1][2]) {
            money = money * 1.5;
            out = "Two the same. +1.5x!";
        } else {
            money = money * -1;
            out = "You lost. Better luck next time!";
        }
        msgchan.sendMessage(out + " Winnings: " + Double.toString(money) + "\n" + ba.getBalance() + "-->" + (ba.getBalance() + money) + " Dodos").queue();

        ba.setBalance(ba.getBalance() + money);

        BankUtils.m_bankService.save(ba);
    }
}
