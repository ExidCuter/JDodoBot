package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.REST.service.SubServiceImpl;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.config.BotConfig;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.Subscription;

import java.util.List;

import static xyz.the_dodo.config.CommandConfig.commands;

public class SubsUtils {
    private static int tick = 0;
    public static SubServiceImpl subService = BeanUtils.getBean(SubServiceImpl.class);
    public static BotConfig config = BeanUtils.getBean(BotConfig.class);

    public static List<Subscription> getSubscriptionsForGuild(Guild guild) {
        return subService.getAllSubscriptionOfServerDiscordId(guild.getId());
    }

    public static void addSubscriptionToGuild(String command, int timer, Guild guild, TextChannel channel) {
        Server server;
        Subscription subscription;

        subscription = new Subscription();

        if (!ServerUtils.serverExist(guild))
            ServerUtils.createServer(guild);

        server = ServerUtils.serverService.findByDiscordId(guild.getId());

        subscription.setTimer(timer);
        subscription.setServer(server);
        subscription.setCommand(command);
        subscription.setChannelId(channel.getId());

        subService.save(subscription);
    }

    public static void removeSubscriptionFromGuild(int id, Guild guild) {
        List<Subscription> subscriptions;

        subscriptions = subService.getAllSubscriptionOfServerDiscordId(guild.getId());

        for (Subscription subscription : subscriptions) {
            if (subscription.getId() == id) {
                subService.delete(subscription);

                return;
            }
        }
    }

    public static void triggerSubs() {
        subService.findAll().forEach(subscription -> {
            Guild guild;
            User user;
            MessageParams messageParams;

            user = config.getBotAsMember().getUser();
            guild = config.getGuildById(subscription.getServer().getDiscordId());

            if (tick % subscription.getTimer() == 0) {

                messageParams = new MessageParams(subscription.getCommand(), user, guild, guild.getTextChannelById(subscription.getChannelId()));

                commands.values().forEach(command -> {
                    if (subscription.getCommand().startsWith(command.getCommand())) {
                        command.trigger(messageParams);
                    }
                });
            }
        });

        if (tick == Integer.MAX_VALUE)
            tick = 0;
        else
            tick++;
    }
}
