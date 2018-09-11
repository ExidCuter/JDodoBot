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
import xyz.dodo.fortnite.Fortnite;
import xyz.the_dodo.bot.functions.misc.FortniteRecords;
import xyz.the_dodo.bot.listeners.CommandListener;
import xyz.the_dodo.bot.listeners.OnAddedToServerListener;
import xyz.the_dodo.bot.listeners.OnServerJoinListener;
import xyz.the_dodo.bot.listeners.StatsListener;
import xyz.the_dodo.bot.utils.CommandHandler;
import xyz.the_dodo.bot.utils.ImageUtils;
import com.kdotj.simplegiphy.SimpleGiphy;
import xyz.the_dodo.bot.utils.VoiceUtils;

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
                .addEventListener(new StatsListener())
                .addEventListener(new CommandListener())
                .addEventListener(new OnServerJoinListener())
                .addEventListener(new OnAddedToServerListener())
                .build();

        SpringApplication.run(DodoBot.class, args);

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
}

class Initiator {
    private String m_token;
    private String m_giphyToken;
    private VoiceUtils voiceUtils;

    public void setToken(String p_token) {
        m_token = p_token;
    }

    public String getToken() {
        return m_token;
    }

    public String getGiphyToken() {
        return m_giphyToken;
    }

    public VoiceUtils getVoiceUtils() {
        return voiceUtils;
    }

    public Initiator() throws IOException {
        List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
        try {
            m_token = settings.get(0);
            m_giphyToken = settings.get(1);

            FortniteRecords.setFortnite(new Fortnite(settings.get(2)));

            SimpleGiphy.setApiKey(m_giphyToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.setProperty("http.agent", m_token);

        CommandHandler.registerCommands();

        ImageUtils.gif = new BufferedImage[35];
        ImageUtils.triggered = ImageUtils.getBufferedImageFromFile("triggered.jpg");

        for (int i = 0; i < ImageUtils.gif.length; i++) {
            ImageUtils.gif[i] = ImageUtils.getBufferedImageFromFile("gif/"+String.valueOf(i)+".png");
        }

        voiceUtils = new VoiceUtils();
    }
}