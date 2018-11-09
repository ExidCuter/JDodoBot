package xyz.the_dodo.bot.utils;

import xyz.the_dodo.REST.service.RulesServiceImpl;
import xyz.the_dodo.database.interfaces.services.IRulesService;

public class RulesUtils {
    public static IRulesService m_serverService = BeanUtils.getBean(RulesServiceImpl.class);

    public boolean rulesExist(String discordId) {
        return m_serverService.getRulesByServerDiscordId(discordId) != null;
    }
}
