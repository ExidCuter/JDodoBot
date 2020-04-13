package xyz.the_dodo.bot.types;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.dodo.fortnite.Fortnite;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.misc.FortniteRecords;
import xyz.the_dodo.bot.functions.misc.FortniteRecordsImage;
import xyz.the_dodo.bot.listeners.MentionListener;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
@Getter
@NoArgsConstructor
public class Initiator {

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

        CommandHandler.registerCommands();

        MentionListener.CLEVERBOT_API_KEY = cleverBotToken.equals("TOKEN") ? null : cleverBotToken;

        ImageUtils.gif = new BufferedImage[35];
        ImageUtils.triggered = ImageUtils.getBufferedImageFromFile("src/main/resources/img/triggered.jpg");

        ImageUtils.fortniteBG = ImageUtils.getBufferedImageFromFile("src/main/resources/img/bg.jpg");

        for (int i = 0; i < ImageUtils.gif.length; i++) {
            ImageUtils.gif[i] = ImageUtils.getBufferedImageFromFile("src/main/resources/img/gif/" + String.valueOf(i) + ".png");
        }

        voiceUtils = new VoiceUtils();

        DodoBot.init = this;
    }

    public static BufferedImage generateBotAvatar() throws IOException {
        Graphics2D g2;
        BufferedImage avatar, temp;

        temp = ImageUtils.getBufferedImageFromUrl(DodoBot.getBotAvatarUrl());
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
