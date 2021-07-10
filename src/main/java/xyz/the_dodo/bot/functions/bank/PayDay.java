package xyz.the_dodo.bot.functions.bank;

import net.dv8tion.jda.api.entities.User;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.response.BotResponse;
import xyz.the_dodo.bot.types.response.BotResponseTypeEnum;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.BankUtils;
import xyz.the_dodo.database.types.BankAccount;

import java.time.LocalDateTime;

@BotService(command = "payday", description = "PAYDAY!!!!", usage = "payday", category = CommandCategoryEnum.BANK)
public class PayDay extends IFunction {
    public PayDay(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        User user;
        BankAccount ba;

        user = messageParams.getUser();
        if (BankUtils.bankAccountExists(messageParams.getUser())) {
            ba = BankUtils.bankService.findByUserDiscordId(user.getId());

            if (ba.getLastPay() == null || ba.getLastPay().plusDays(1L).isBefore(LocalDateTime.now())) {
                ba.setBalance(ba.getBalance() + 100);
                ba.setLastPay(LocalDateTime.now());

                BankUtils.bankService.save(ba);

                this.responseQueue.add(new BotResponse(BotResponseTypeEnum.TEXT, "You received 100 ₪.", messageParams.getTextChannel()));
            } else {
                this.responseQueue.add(new BotResponse(BotResponseTypeEnum.TEXT, "You already got your pay!", messageParams.getTextChannel()));
            }
        } else {
            this.responseQueue.add(new BotResponse(BotResponseTypeEnum.TEXT, "You need to register an account!", messageParams.getTextChannel()));
        }

        return this;
    }
}
