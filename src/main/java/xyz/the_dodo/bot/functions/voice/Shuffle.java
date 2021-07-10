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

@BotService(command = "shuffle", description = "Shuffles the playing queue", usage = "shuffle", category = CommandCategoryEnum.VOICE)
public class Shuffle extends IFunction {
    private static VoiceUtils voiceUtils = BeanUtils.getBean(VoiceUtils.class);

    public Shuffle(String command, String description, String usage, boolean isService, CommandCategoryEnum commandCategoryEnum) {
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
            scheduler.shuffle();
            messageParams.getTextChannel().sendMessage("The queue has been shuffled!").queue();
        } else {
            messageParams.getTextChannel().sendMessage("The queue is currently empty!").queue();
        }

        return this;
    }
}
