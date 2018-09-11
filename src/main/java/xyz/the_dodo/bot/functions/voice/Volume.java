package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;

public class Volume extends IFunction {
    public Volume(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        AudioPlayer player;
        int newVolume, oldVolume;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();
        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);
        player = musicManager.player;

        if (p_messageParams.getParameters().length > 0) {
            try {
                oldVolume = player.getVolume();
                newVolume = Math.max(10, Math.min(100, Integer.parseInt(p_messageParams.getParameters()[0])));

                player.setVolume(newVolume);

                p_messageParams.getTextChannel().sendMessage("Player volume changed from `" + oldVolume + "` to `" + newVolume + "`").queue();
            } catch (NumberFormatException e) {
                p_messageParams.getTextChannel().sendMessage("`" +p_messageParams.getParameters()[0] + "` is not a valid integer. (10 - 100)").queue();
            }
        } else {
            p_messageParams.getTextChannel().sendMessage("Current player volume: **" + player.getVolume() + "**").queue();
        }
    }
}
