package xyz.the_dodo.bot.functions.voice;

import net.dv8tion.jda.api.entities.Guild;
import xyz.the_dodo.bot.anotations.BotService;
import xyz.the_dodo.bot.functions.IFunction;
import xyz.the_dodo.bot.types.message.CommandCategoryEnum;
import xyz.the_dodo.bot.types.audio.GuildMusicManager;
import xyz.the_dodo.bot.types.message.MessageParams;
import xyz.the_dodo.bot.types.audio.TrackScheduler;
import xyz.the_dodo.bot.utils.BeanUtils;
import xyz.the_dodo.bot.utils.VoiceUtils;

@BotService(command = "skip", description = "Skips current item in queue", usage = "skip", category = CommandCategoryEnum.VOICE)
public class Skip extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Skip(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
        super(command, description, usage, isService, commandCategoryEnum);
    }

    @Override
    public IFunction prepare(MessageParams messageParams) {
        Guild guild;
        TrackScheduler scheduler;
        GuildMusicManager musicManager;

        guild = messageParams.getGuild();
        musicManager = voiceUtils.getMusicManager(guild);
        scheduler = musicManager.scheduler;

        if (!scheduler.isEmpty()) {
            scheduler.nextTrack();
            messageParams.getTextChannel().sendMessage("The current track was skipped.").queue();
        } else {
            messageParams.getTextChannel().sendMessage("Queue is empty.").queue();
        }

        return this;
    }
}
