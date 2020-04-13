package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.User;
import org.jetbrains.annotations.NotNull;
import xyz.the_dodo.REST.service.BankServiceImpl;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.types.BankAccount;

import java.time.LocalDateTime;

public class BankUtils {
    public static IBankService bankService = BeanUtils.getBean(BankServiceImpl.class);

    public static boolean bankAccountExists(@NotNull User user) {
        BankAccount ba;

        ba = bankService.findByUserDiscordId(user.getId());

        return ba != null;
    }

    public static void createBankAccount(@NotNull User owner) {
        BankAccount ba;
        xyz.the_dodo.database.types.User user;

        ba = new BankAccount();
        user = UserUtils.userService.findByDiscordId(owner.getId());

        if (user == null) {
            user = new xyz.the_dodo.database.types.User();
            user.setDiscordId(owner.getId());

            UserUtils.userService.save(user);
        }

        ba.setBalance(100D);
        ba.setLastPay(LocalDateTime.now());
        ba.setUser(user);

        bankService.save(ba);
    }
}
