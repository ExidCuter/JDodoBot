package xyz.the_dodo;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import xyz.the_dodo.bot.listeners.*;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.SubsUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;
import xyz.the_dodo.config.Initiator;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Configuration
@SpringBootApplication
@RequiredArgsConstructor
public class DodoBot {
    private static JDA bot;

    public static Initiator init;

    public static final String version = "3.0-BETA";
    public static final String botOwner = "161795217803051008"; //Owner of the bot
    public static final int maxMessagesCached = 10000; //you can set custom amount (Higher you go -> more memory usage!)

    public static void main(String[] args) throws LoginException, IOException, InterruptedException {
        Timer timer;
        JDABuilder jdaBuilder;

        timer = new Timer();

        SpringApplication.run(DodoBot.class, args);

        jdaBuilder = new JDABuilder(AccountType.BOT)
                .setToken(init.getToken())
                .addEventListener(new StatsListener())
                .addEventListener(new DeleteListener()) //High Memory usage if enabled
                .addEventListener(new CommandListener())
                .addEventListener(new OnServerJoinListener())
                .addEventListener(new OnAddedToServerListener());

        if (MentionListener.CLEVERBOT_API_KEY != null)
            jdaBuilder.addEventListener(new MentionListener());

        bot = jdaBuilder.build();

        bot.awaitReady();

        bot.getPresence().setGame(Game.playing("!help for HELP"));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SubsUtils.triggerSubs();
            }
        }, 0, 10 * 60 * 1000);

        try {
            ImageUtils.botAvatar = Initiator.generateBotAvatar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNumOfServers() {
        return bot.getGuilds().size();
    }

    public static Member getBotAsMember() {
        return bot.getGuilds().size() > 0 ? bot.getGuilds().get(0).getMemberById(bot.getSelfUser().getId()) : null;
    }

    public static List<Guild> getGuilds() {
        return bot.getGuilds();
    }

    public static Guild getGuildById(String id) {
        return bot.getGuildById(id);
    }

    public static void leaveGuild(String id) {
        bot.getGuildById(id).leave().queue();
    }

    public static VoiceUtils getVoiceUtils() {
        return init.getVoiceUtils();
    }

    public static String getBotAvatarUrl() {
        return bot.getSelfUser().getAvatarUrl();
    }

    public static String getName() {
        return bot.getSelfUser().getName();
    }
}
