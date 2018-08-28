package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.REST.service.BankServiceImpl;
import xyz.the_dodo.database.interfaces.services.IBankService;
import xyz.the_dodo.database.types.BankAccount;

import java.time.LocalDateTime;


public class BankUtils {
    public static IBankService m_bankService = BeanUtils.getBean(BankServiceImpl.class);

    public static boolean bankAccoutnExists(User p_user) {
        BankAccount ba;

        ba = m_bankService.findByUserDiscordId(p_user.getId());

        return ba != null;
    }

    public static void createBankAccount(User p_user) {
        BankAccount ba;
        xyz.the_dodo.database.types.User user;

        ba = new BankAccount();
        user = UserUtils.m_userService.findByDiscordId(p_user.getId());

        if(user == null) {
            user = new xyz.the_dodo.database.types.User();
            user.setDiscordId(p_user.getId());

            UserUtils.m_userService.save(user);
        }

        ba.setBalance(100D);
        ba.setLastPay(LocalDateTime.now());
        ba.setUser(user);

        m_bankService.save(ba);
    }
}
