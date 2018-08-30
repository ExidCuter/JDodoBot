package xyz.the_dodo.bot.utils;

import net.dv8tion.jda.core.entities.Guild;
import xyz.the_dodo.REST.service.DefaultRoleServiceImpl;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

public class DefaultRoleUtils {
    private static DefaultRoleServiceImpl m_defaultRoleService = BeanUtils.getBean(DefaultRoleServiceImpl.class);

    public static boolean defaultRoleExists(Server p_server) {
        return getDefaultRoleOfServer(p_server) != null;
    }

    public static void saveDefaultRole(DefaultRole p_defaultRole) {
        DefaultRole defaultRole;

        defaultRole = m_defaultRoleService.findByServerId(p_defaultRole.getServer().getId());

        if (defaultRole != null) {
            m_defaultRoleService.delete(defaultRole);
        }

        m_defaultRoleService.save(p_defaultRole);
    }

    public static DefaultRole getDefaultRoleOfServer(Server p_server) {
        DefaultRole defaultRole;

        defaultRole = m_defaultRoleService.findByServerId(p_server.getId());

        return defaultRole;
    }

}
