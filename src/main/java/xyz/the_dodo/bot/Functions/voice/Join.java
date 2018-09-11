package xyz.the_dodo.bot.Functions.voice;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.Functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.bot.utils.VoiceUtils;

public class Join extends IFunction {
    public Join(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        Guild guild;
        VoiceChannel voiceChannel;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();

        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);

        if (p_messageParams.getParameters().length == 0) { //No channel name was provided to search for.
            //TODO: check if user is in channel
            p_messageParams.getTextChannel().sendMessage("No channel name was provided to search with to join.").queue();
        } else {
            voiceChannel = null;
            try {
                voiceChannel = p_messageParams.getGuild().getVoiceChannelById(p_messageParams.getParameters()[0]);
            } catch (Exception ignored) {
            }

            if (voiceChannel == null)
                voiceChannel = p_messageParams.getGuild().getVoiceChannelsByName(p_messageParams.getParameters()[0], true).stream().findFirst().orElse(null);
            if (voiceChannel == null) {
                p_messageParams.getTextChannel().sendMessage("Could not find VoiceChannel by name: " + p_messageParams.getParameters()[0]).queue();
            } else {
                guild.getAudioManager().setSendingHandler(musicManager.sendHandler);

                try {
                    guild.getAudioManager().openAudioConnection(voiceChannel);
                } catch (PermissionException e) {
                    if (e.getPermission() == Permission.VOICE_CONNECT) {
                        p_messageParams.getTextChannel().sendMessage("DodoBot does not have permission to connect to: " + voiceChannel.getName()).queue();
                    }
                }
            }
        }

    }
}
