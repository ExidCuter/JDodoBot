package xyz.the_dodo;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.the_dodo.bot.listeners.CommandListner;

import javax.security.auth.login.LoginException;


@SpringBootApplication
public class DodoBot {
    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("MzM4NjU5MTQ2MzMxMzI0NDE2.Dkw44w.pg-HzCDTC_o_nwPDxYOhloNbel0")
                .addEventListener(new CommandListner())
                .build();

        SpringApplication.run(DodoBot.class, args);
    }
}
