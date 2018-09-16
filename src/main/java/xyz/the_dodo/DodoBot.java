package xyz.the_dodo;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import xyz.the_dodo.bot.listeners.*;
import xyz.the_dodo.bot.types.Initiator;
import xyz.the_dodo.bot.utils.DeletedMessageUtils;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableSpringConfigured
@SpringBootApplication
public class DodoBot {
    private static JDA bot;
    private static Initiator init;

    public static final String verzion = "2.0-ALPHA";
    public static final int maxMessagesCached = 10000; //you can set custom amount (Higher you go -> more memory usage!)

    public static void main(String[] args) throws LoginException, IOException
    {
        init = new Initiator();

        bot = new JDABuilder(AccountType.BOT)
                .setToken(init.getToken())
                .addEventListener(new StatsListener())
                .addEventListener(new DeleteListener()) //High Memory usage if enabled
                .addEventListener(new CommandListener())
                .addEventListener(new OnServerJoinListener())
                .addEventListener(new OnAddedToServerListener())
                .build();

        SpringApplication.run(DodoBot.class, args);

        ImageUtils.botAvatar = Initiator.generateBotAvatar();

        bot.getPresence().setGame(Game.playing("!help for HELP"));
    }

    public static int getNumOfServers() {
        return bot.getGuilds().size();
    }

    public static List<Guild> getGuilds() {
        return bot.getGuilds();
    }

    public static VoiceUtils getVoiceUtils() {
        return init.getVoiceUtils();
    }

    public static String getBotAvatarUrl() {
        return bot.getSelfUser().getAvatarUrl();
    }
}