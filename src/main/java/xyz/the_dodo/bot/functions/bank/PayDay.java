package xyz.the_dodo.bot.functions.bank;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

import java.time.LocalDateTime;

public class PayDay extends IFunction {
    public PayDay(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.BANK;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        User user;
        BankAccount ba;

        user = p_messageParams.getUser();
        if (BankUtils.bankAccountExists(p_messageParams.getUser())) {
            ba = BankUtils.m_bankService.findByUserDiscordId(user.getId());

            if (ba.getLastPay().plusDays(1L).isBefore(LocalDateTime.now())) {
                ba.setBalance(ba.getBalance() + 100);
                ba.setLastPay(LocalDateTime.now());

                BankUtils.m_bankService.save(ba);

                p_messageParams.getTextChannel().sendMessage("You received 100 ₪.").queue();
            } else
                p_messageParams.getTextChannel().sendMessage("You already got your pay!").queue();
        } else
            p_messageParams.getTextChannel().sendMessage("You need to register an account!").queue();
    }
}
