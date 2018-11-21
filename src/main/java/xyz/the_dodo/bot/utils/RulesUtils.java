package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.RulesServiceImpl;
import xyz.the_dodo.database.interfaces.services.IRulesService;
import xyz.the_dodo.database.types.Rules;

public class RulesUtils {
    private static IRulesService m_serverService = BeanUtils.getBean(RulesServiceImpl.class);

    public static boolean rulesExist(Guild guild) {
        return m_serverService.getRulesByServerDiscordId(guild.getId()) != null;
    }

    public static void saveRules(Rules p_rules) {
        m_serverService.save(p_rules);
    }

    public static Rules findByGuild(Guild p_guild) {
        return m_serverService.getRulesByServerDiscordId(p_guild.getId());
    }

    public static void deleteRules(Guild p_guild) {
        m_serverService.delete(findByGuild(p_guild));
    }
}
