package xyz.the_dodo;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import xyz.the_dodo.bot.listeners.CommandListner;
import xyz.the_dodo.bot.utils.CommandHandler;
import xyz.the_dodo.bot.utils.ImageUtils;

import javax.security.auth.login.LoginException;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Configuration
@EnableSpringConfigured
@SpringBootApplication
public class DodoBot {
    public static void main(String[] args) throws LoginException, IOException
    {
        String token = "MzM4NjU5MTQ2MzMxMzI0NDE2.Dkw44w.pg-HzCDTC_o_nwPDxYOhloNbel0";
        System.setProperty("http.agent", token );

        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListener(new CommandListner())
                .build();

        CommandHandler.registerCommands();

        ImageUtils.gif = new BufferedImage[35];
        ImageUtils.triggered = ImageUtils.getBufferedImageFromFile("triggered.jpg");

        for (int i = 0; i < ImageUtils.gif.length; i++) {
            ImageUtils.gif[i] = ImageUtils.getBufferedImageFromFile("gif/"+String.valueOf(i)+".png");
        }

        SpringApplication.run(DodoBot.class, args);
    }
}
