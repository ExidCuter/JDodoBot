package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.CommandCategoryEnum;
import xyz.the_dodo.bot.types.GuildMusicManager;
import xyz.the_dodo.bot.types.MessageParams;

@BotService(command = "join", description = "Joins a voice channel", usage = "join <CHANNEL NAME/CHANNEL ID>", category = CommandCategoryEnum.VOICE)
public class Join extends IFunction {
    public Join(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public void trigger(MessageParams messageParams) {
        //TODO: check if user is in channel
        Guild guild;
        VoiceChannel voiceChannel;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();

        musicManager = DodoBot.getVoiceUtils().getMusicManager(guild);

        if (messageParams.getParameters().length == 0)  //No channel name was provided to search for.
            messageParams.getTextChannel().sendMessage("No channel name was provided to search with to join.").queue();
        else {
            voiceChannel = null;
            try {
                voiceChannel = messageParams.getGuild().getVoiceChannelById(messageParams.getParameters()[0]);
            } catch (Exception ignored) {
            }

            if (voiceChannel == null)
                voiceChannel = messageParams.getGuild().getVoiceChannelsByName(messageParams.getContent(), true).stream().findFirst().orElse(null);

            if (voiceChannel == null)
                messageParams.getTextChannel().sendMessage("Could not find VoiceChannel by name: " + messageParams.getContent()).queue();
            else {
                guild.getAudioManager().setSendingHandler(musicManager.sendHandler);

                try {
                    guild.getAudioManager().openAudioConnection(voiceChannel);
                } catch (PermissionException e) {
                    if (e.getPermission() == Permission.VOICE_CONNECT)
                        messageParams.getTextChannel().sendMessage("DodoBot does not have permission to connect to: " + voiceChannel.getName()).queue();
                }
            }
        }

    }
}
