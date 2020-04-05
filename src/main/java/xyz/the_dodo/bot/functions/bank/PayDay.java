package xyz.the_dodo.bot.functions.bank;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

import java.time.LocalDateTime;

@BotService(command = "payday", description = "PAYDAY!!!!", usage = "payday", category = CommandCategoryEnum.BANK)
public class PayDay extends IFunction {
    public PayDay(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        User user;
        BankAccount ba;

        user = messageParams.getUser();
        if (BankUtils.bankAccountExists(messageParams.getUser())) {
            ba = BankUtils.m_bankService.findByUserDiscordId(user.getId());

            if (ba.getLastPay().plusDays(1L).isBefore(LocalDateTime.now())) {
                ba.setBalance(ba.getBalance() + 100);
                ba.setLastPay(LocalDateTime.now());

                BankUtils.m_bankService.save(ba);

                messageParams.getTextChannel().sendMessage("You received 100 ₪.").queue();
            } else
                messageParams.getTextChannel().sendMessage("You already got your pay!").queue();
        } else
            messageParams.getTextChannel().sendMessage("You need to register an account!").queue();
    }
}
