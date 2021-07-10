package xyz.the_dodo.bot.functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "volume", description = "Changes the volume of the player", usage = "volume <10-100>", category = CommandCategoryEnum.VOICE)
public class Volume extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Volume(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        AudioPlayer player;
        int newVolume, oldVolume;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        player = musicManager.player;

        if (messageParams.getParameters().length > 0) {
            try {
                oldVolume = player.getVolume();
                newVolume = Math.max(10, Math.min(100, Integer.parseInt(messageParams.getParameters()[0])));

                player.setVolume(newVolume);

                messageParams.getTextChannel().sendMessage("Player volume changed from `" + oldVolume + "` to `" + newVolume + "`").queue();
            } catch (NumberFormatException e) {
                messageParams.getTextChannel().sendMessage("`" + messageParams.getParameters()[0] + "` is not a valid integer. (10 - 100)").queue();
            }
        } else {
            messageParams.getTextChannel().sendMessage("Current player volume: **" + player.getVolume() + "**").queue();
        }

        return this;
    }
}
