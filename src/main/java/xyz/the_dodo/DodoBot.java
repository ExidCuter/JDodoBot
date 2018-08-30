package xyz.the_dodo;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import xyz.the_dodo.bot.listeners.CommandListener;
import xyz.the_dodo.bot.listeners.OnAddedToServerListener;
import xyz.the_dodo.bot.listeners.OnServerJoinListener;
import xyz.the_dodo.bot.utils.CommandHandler;
import xyz.the_dodo.bot.utils.ImageUtils;

import javax.security.auth.login.LoginException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@EnableSpringConfigured
@SpringBootApplication
public class DodoBot {
    private static JDA bot;
    private static Initiator init;

    public static final String verzion = "2.0-ALPHA";

    public static void main(String[] args) throws LoginException, IOException
    {
        init = new Initiator();

        bot = new JDABuilder(AccountType.BOT)
                .setToken(init.getToken())
                .addEventListener(new CommandListener())
                .addEventListener(new OnAddedToServerListener())
                .addEventListener(new OnServerJoinListener())
                .build();

        SpringApplication.run(DodoBot.class, args);
    }

    public static int getNumOfServers() {
        return bot.getGuilds().size();
    }

    public static List<Guild> getGuilds() {
        return bot.getGuilds();
    }
}

class Initiator {
    private String token;

    public void setToken(String p_token) {
        token = p_token;
    }

    public String getToken() {
        return token;
    }

    public Initiator() throws IOException {
        List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
        token = settings.get(0);
        System.setProperty("http.agent", token);

        CommandHandler.registerCommands();

        ImageUtils.gif = new BufferedImage[35];
        ImageUtils.triggered = ImageUtils.getBufferedImageFromFile("triggered.jpg");

        for (int i = 0; i < ImageUtils.gif.length; i++) {
            ImageUtils.gif[i] = ImageUtils.getBufferedImageFromFile("gif/"+String.valueOf(i)+".png");
        }
    }
}