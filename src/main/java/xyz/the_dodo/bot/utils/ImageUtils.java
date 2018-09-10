package xyz.the_dodo.bot.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ImageUtils {
    public static BufferedImage[] gif;

    public static BufferedImage triggered;

    public static ByteArrayOutputStream generateShoot(String p_userImage) {
        try {
            ByteArrayOutputStream os;
            AnimatedGifEncoder e;
            BufferedImage avatar;

            e = new AnimatedGifEncoder();
            os = new ByteArrayOutputStream();
            avatar = getBufferedImageFromUrl(p_userImage);

            e.start(os);
            e.repeat = 100;
            e.transparent = new Color(0, 0, 0, 0);
            e.setDelay(70);

            for (int i = 0; i < gif.length; i++) {
                if (i > 21) drawImg(gif[i], avatar, 30 - (10 * (i - 20)), 42 + (11 * (i - 20)), 110, 98);
                else drawImg(gif[i], avatar, 30, 42, 110, 98);
                e.addFrame(gif[i]);
            }

            e.finish();

            return os;

        } catch (IOException e) {
            return null;
        }
    }

    public static ByteArrayOutputStream generateTriggered(String p_userImage) {
        try {
            ByteArrayOutputStream os;
            AnimatedGifEncoder e;
            BufferedImage canvas, avatar;

            e = new AnimatedGifEncoder();
            os = new ByteArrayOutputStream();
            avatar = getBufferedImageFromUrl(p_userImage);
            triggered = getBufferedImageFromFile("triggered.jpg");
            canvas = new BufferedImage(avatar.getHeight() + 5, avatar.getHeight() + 5, BufferedImage.TYPE_INT_ARGB);

            prepareTriggeredCanvas(canvas);

            e.start(os);
            e.repeat = 100;
            e.transparent = new Color(0, 0, 0, 0);
            e.setDelay(40);

            drawImg(canvas, avatar, 5, 5, avatar.getWidth(), avatar.getHeight());
            drawImg(canvas, triggered, 0, avatar.getHeight() - 30, avatar.getWidth(), 20);
            e.addFrame(canvas);
            canvas = new BufferedImage(avatar.getHeight() + 10, avatar.getHeight() + 10, BufferedImage.TYPE_INT_ARGB);
            prepareTriggeredCanvas(canvas);

            drawImg(canvas, avatar, 0, 5, avatar.getWidth(), avatar.getHeight());
            drawImg(canvas, triggered, 5, avatar.getHeight() - 25, avatar.getWidth(), 20);
            e.addFrame(canvas);
            canvas = new BufferedImage(avatar.getHeight() + 10, avatar.getHeight() + 10, BufferedImage.TYPE_INT_ARGB);
            prepareTriggeredCanvas(canvas);

            drawImg(canvas, avatar, 5, 0, avatar.getWidth(), avatar.getHeight());
            drawImg(canvas, triggered, 0, avatar.getHeight() - 20, avatar.getWidth(), 20);
            e.addFrame(canvas);
            canvas = new BufferedImage(avatar.getHeight() + 10, avatar.getHeight() + 10, BufferedImage.TYPE_INT_ARGB);
            prepareTriggeredCanvas(canvas);

            drawImg(canvas, avatar, 0, 0, avatar.getWidth(), avatar.getHeight());
            drawImg(canvas, triggered, 5, avatar.getHeight() - 25, avatar.getWidth(), 20);
            e.addFrame(canvas);
            canvas = new BufferedImage(avatar.getHeight() + 10, avatar.getHeight() + 10, BufferedImage.TYPE_INT_ARGB);
            prepareTriggeredCanvas(canvas);

            drawImg(canvas, avatar, 5, 5, avatar.getWidth(), avatar.getHeight());
            drawImg(canvas, triggered, 0, avatar.getHeight() - 30, avatar.getWidth(), 20);
            e.addFrame(canvas);

            e.finish();

            return os;
        } catch (IOException e) {
            return null;
        }
    }

    public static BufferedImage getBufferedImageFromFile(String p_fileName) throws IOException {
        BufferedImage img;

        img = ImageIO.read(new File(p_fileName));

        return img;
    }

    private static BufferedImage getBufferedImageFromUrl(String p_url) throws IOException {
        BufferedImage img;

        URL url = new URL(p_url);
        img = ImageIO.read(url);

        return img;
    }

    private static void drawImg(BufferedImage img1, BufferedImage img2, int x, int y, int width, int hight) {
        Graphics2D g2d = img1.createGraphics();
        g2d.drawImage(img2, x, y, width, hight, (ImageObserver) null);
        g2d.dispose();
    }

    private static void prepareTriggeredCanvas(BufferedImage canvas) {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g2d.dispose();
    }
}
