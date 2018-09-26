package xyz.the_dodo.bot.types;

import com.kdotj.simplegiphy.SimpleGiphy;
import xyz.dodo.fortnite.Fortnite;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.misc.FortniteRecords;
import xyz.the_dodo.bot.functions.misc.FortniteRecordsImage;
import xyz.the_dodo.bot.listeners.MentionListener;
import xyz.the_dodo.bot.utils.CommandHandler;
import xyz.the_dodo.bot.utils.ImageUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Initiator {
    private String m_token;
    private String m_giphyToken;
    private String m_cleverBotToken;
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

    public String getCleverBotToken() {
        return m_cleverBotToken;
    }

    public Initiator() throws IOException {
        List<String> settings = Files.readAllLines(Paths.get("settings.txt"));
        try {
            m_token = settings.get(0);
            m_giphyToken = settings.get(1);

            SimpleGiphy.setApiKey(m_giphyToken);

            Fortnite fortnite = new Fortnite(settings.get(2));

            FortniteRecords.setFortnite(fortnite);
            FortniteRecordsImage.setFortnite(fortnite);

            m_cleverBotToken = settings.get(4);

            MentionListener.CleverBot_API_Key = m_cleverBotToken;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.setProperty("http.agent", m_token);

        CommandHandler.registerCommands();

        ImageUtils.gif = new BufferedImage[35];
        ImageUtils.triggered = ImageUtils.getBufferedImageFromFile("img/triggered.jpg");

        ImageUtils.fortniteBG = ImageUtils.getBufferedImageFromFile("img/bg.jpg");

        for (int i = 0; i < ImageUtils.gif.length; i++) {
            ImageUtils.gif[i] = ImageUtils.getBufferedImageFromFile("img/gif/" + String.valueOf(i) + ".png");
        }

        voiceUtils = new VoiceUtils();
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
