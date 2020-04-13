package xyz.the_dodo.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.dodo.fortnite.Fortnite;
import xyz.the_dodo.bot.functions.misc.FortniteRecords;
import xyz.the_dodo.bot.functions.misc.FortniteRecordsImage;
import xyz.the_dodo.bot.listeners.*;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.SubsUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Getter
@NoArgsConstructor
public class BotConfig {
    private JDA bot;

    private final String version = "3.0-BETA";
    private final String botOwner = "161795217803051008"; //Owner of the bot
    private final int maxMessagesCached = 10000; //you can set custom amount (Higher you go -> more memory usage!)

    @Value("${dodo-bot.token}")
    private String token;

    @Value("${dodo-bot.giphy-token}")
    private String giphyToken;

    @Value("${dodo-bot.clever-token}")
    private String cleverBotToken;

    @Value("${dodo-bot.trn-token}")
    private String trnToken;

    private VoiceUtils voiceUtils;

    @PostConstruct
    private void init() throws Exception {
        System.setProperty("http.agent", token);

        Fortnite fortnite = new Fortnite(trnToken);

        FortniteRecords.setFortnite(fortnite);
        FortniteRecordsImage.setFortnite(fortnite);

        CommandConfig.registerCommands();

        MentionListener.CLEVERBOT_API_KEY = cleverBotToken;

        ImageUtils.gif = new BufferedImage[35];
        ImageUtils.triggered = ImageUtils.getBufferedImageFromFile("src/main/resources/img/triggered.jpg");

        ImageUtils.fortniteBG = ImageUtils.getBufferedImageFromFile("src/main/resources/img/bg.jpg");

        for (int i = 0; i < ImageUtils.gif.length; i++) {
            ImageUtils.gif[i] = ImageUtils.getBufferedImageFromFile("src/main/resources/img/gif/" + String.valueOf(i) + ".png");
        }

        voiceUtils = new VoiceUtils();

        Timer timer;
        JDABuilder jdaBuilder;

        timer = new Timer();

        jdaBuilder = new JDABuilder(AccountType.BOT)
                .setToken(this.getToken())
                .addEventListener(new StatsListener())
                .addEventListener(new DeleteListener()) //High Memory usage if enabled
                .addEventListener(new CommandListener())
                .addEventListener(new OnServerJoinListener())
                .addEventListener(new OnAddedToServerListener());

        if (!cleverBotToken.equals("TOKEN"))
            jdaBuilder.addEventListener(new MentionListener());

        this.bot = jdaBuilder.build();

        this.bot.awaitReady();

        this.bot.getPresence().setGame(Game.streaming("!help for HELP", "https://github.com/ExidCuter/JDodoBot"));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SubsUtils.triggerSubs();
            }
        }, 0, 10 * 60 * 1000);

        try {
            ImageUtils.botAvatar = this.generateBotAvatar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumOfServers() {
        return this.bot.getGuilds().size();
    }

    @Nullable
    public Member getBotAsMember() {
        return this.bot.getGuilds().size() > 0 ? this.bot.getGuilds().get(0).getMemberById(bot.getSelfUser().getId()) : null;
    }

    public List<Guild> getGuilds() {
        return this.bot.getGuilds();
    }

    public Guild getGuildById(String id) {
        return this.bot.getGuildById(id);
    }

    public void leaveGuild(String id) {
        this.bot.getGuildById(id).leave().queue();
    }

    public String getBotAvatarUrl() {
        return this.bot.getSelfUser().getAvatarUrl();
    }

    public String getName() {
        return this.bot.getSelfUser().getName();
    }

    public BufferedImage generateBotAvatar() throws IOException {
        Graphics2D g2;
        BufferedImage avatar, temp;

        temp = ImageUtils.getBufferedImageFromUrl(this.getBotAvatarUrl());
        avatar = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_ARGB);

        g2 = avatar.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, avatar.getWidth(), avatar.getHeight(), 20, 20));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(temp, 0, 0, null);

        return avatar;
    }
}
