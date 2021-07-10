package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.exceptions.PermissionException;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "join", description = "Joins a voice channel", usage = "join || join <CHANNEL NAME/CHANNEL ID>", category = CommandCategoryEnum.VOICE)
public class Join extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Join(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        VoiceChannel voiceChannel;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        voiceChannel = null;

        musicManager = voiceUtils.getMusicManager(guild);

        if (messageParams.getParameters().length == 0) {
            for (VoiceChannel currVoiceChannel : messageParams.getMessage().getGuild().getVoiceChannels()) {
                if (currVoiceChannel.getMembers().stream().anyMatch(member -> member.getUser().getId().equals(messageParams.getUser().getId()))) {
                    voiceChannel = currVoiceChannel;
                    break;
                }
            }
        } else {
            try {
                voiceChannel = messageParams.getGuild().getVoiceChannelById(messageParams.getParameters()[0]);
            } catch (Exception ignored) {
            }

            if (voiceChannel == null) {
                voiceChannel = messageParams.getGuild().getVoiceChannelsByName(messageParams.getContent(), true).stream().findFirst().orElse(null);
            }
        }

        if (voiceChannel == null) {
            messageParams.getTextChannel().sendMessage("Could not find a Voice Channel").queue();
        } else {
            guild.getAudioManager().setSendingHandler(musicManager.sendHandler);

            try {
                guild.getAudioManager().openAudioConnection(voiceChannel);
            } catch (PermissionException e) {
                if (e.getPermission() == Permission.VOICE_CONNECT) {
                    messageParams.getTextChannel().sendMessage("DodoBot does not have permission to connect to: " + voiceChannel.getName()).queue();
                }
            }
        }

        return this;
    }
}
