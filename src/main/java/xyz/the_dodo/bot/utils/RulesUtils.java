package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.RulesServiceImpl;
import xyz.the_dodo.database.interfaces.services.IRulesService;
import xyz.the_dodo.database.types.Rules;

public class RulesUtils {
    private static IRulesService serverService = BeanUtils.getBean(RulesServiceImpl.class);

    public static boolean rulesExist(Guild guild) {
        return serverService.getRulesByServerDiscordId(guild.getId()) != null;
    }

    public static void saveRules(Rules rules) {
        serverService.save(rules);
    }

    public static Rules findByGuild(Guild guild) {
        return serverService.getRulesByServerDiscordId(guild.getId());
    }

    public static void deleteRules(Guild guild) {
        serverService.delete(findByGuild(guild));
    }
}
