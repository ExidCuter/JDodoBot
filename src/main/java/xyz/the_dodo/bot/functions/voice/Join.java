package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategory;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;

public class Join extends IFunction {
    public Join(String command, String description, String usage) {
        super(command, description, usage);
        commandCategory = CommandCategory.VOICE;
    }

    @Override
    public void trigger(MessageParams p_messageParams) {
        //TODO: check if user is in channel
        Guild guild;
        VoiceChannel voiceChannel;
        GuildMusicManager musicManager;

        guild = p_messageParams.getGuild();

        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);

        if (p_messageParams.getParameters().length == 0)  //No channel name was provided to search for.
            p_messageParams.getTextChannel().sendMessage("No channel name was provided to search with to join.").queue();
        else {
            voiceChannel = null;
            try {
                voiceChannel = p_messageParams.getGuild().getVoiceChannelById(p_messageParams.getParameters()[0]);
            } catch (Exception ignored) { }

            if (voiceChannel == null)
                voiceChannel = p_messageParams.getGuild().getVoiceChannelsByName(p_messageParams.getContent(), true).stream().findFirst().orElse(null);

            if (voiceChannel == null)
                p_messageParams.getTextChannel().sendMessage("Could not find VoiceChannel by name: " + p_messageParams.getContent()).queue();
            else {
                guild.getAudioManager().setSendingHandler(musicManager.sendHandler);

                try {
                    guild.getAudioManager().openAudioConnection(voiceChannel);
                } catch (PermissionException e) {
                    if (e.getPermission() == Permission.VOICE_CONNECT)
                        p_messageParams.getTextChannel().sendMessage("DodoBot does not have permission to connect to: " + voiceChannel.getName()).queue();
                }
            }
        }

    }
}
