package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import xyz.the_dodo.DodoBot;
import xyz.the_dodo.REST.service.SubServiceImpl;
import xyz.the_dodo.bot.types.MessageParams;
import xyz.the_dodo.database.types.Server;
import xyz.the_dodo.database.types.Subscription;

import java.util.List;

import static xyz.the_dodo.bot.types.CommandHandler.commands;

public class SubsUtils {
    private static int tick = 0;
    public static SubServiceImpl m_subService = BeanUtils.getBean(SubServiceImpl.class);

    public static List<Subscription> getSubscriptionsForGuild(Guild guild) {
        return m_subService.getAllSubscriptionOfServerDiscordId(guild.getId());
    }

    public static void addSubscriptionToGuild(String command, int timer, Guild guild, TextChannel channel) {
        Server server;
        Subscription subscription;

        subscription = new Subscription();

        if (!ServerUtils.serverExist(guild))
            ServerUtils.createServer(guild);

        server = ServerUtils.m_serverService.findByDiscordId(guild.getId());

        subscription.setTimer(timer);
        subscription.setServer(server);
        subscription.setCommand(command);
        subscription.setChannelId(channel.getId());

        m_subService.save(subscription);
    }

    public static void removeSubscriptionFromGuild(int id, Guild guild) {
        List<Subscription> subscriptions;

        subscriptions = m_subService.getAllSubscriptionOfServerDiscordId(guild.getId());

        for (Subscription subscription : subscriptions) {
            if (subscription.getId() == id) {
                m_subService.delete(subscription);

                return;
            }
        }
    }

    public static void triggerSubs() {
        m_subService.findAll().forEach(subscription -> {
            Guild guild;
            User user;
            MessageParams messageParams;

            user = DodoBot.getBotAsMember().getUser();
            guild = DodoBot.getGuildById(subscription.getServer().getDiscordId());

            if (tick % subscription.getTimer() == 0) {

                messageParams = new MessageParams(subscription.getCommand(), user, guild, guild.getTextChannelById(subscription.getChannelId()));

                commands.forEach(command -> {
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
