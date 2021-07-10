package xyz.the_dodo.bot.utils;

import xyz.the_dodo.REST.service.DefaultRoleServiceImpl;
import xyz.the_dodo.database.types.DefaultRole;
import xyz.the_dodo.database.types.Server;

public class DefaultRoleUtils {
    public static DefaultRoleServiceImpl defaultRoleService = BeanUtils.getBean(DefaultRoleServiceImpl.class);

    public static boolean defaultRoleExists(Server server) {
        return getDefaultRoleOfServer(server) != null;
    }

    public static void saveDefaultRole(DefaultRole newDefaultRole) {
        DefaultRole defaultRole;

        defaultRole = defaultRoleService.findByServerId(newDefaultRole.getServer().getId());

        if (defaultRole != null) {
            defaultRoleService.delete(defaultRole);
        }

        defaultRoleService.save(newDefaultRole);
    }

    public static DefaultRole getDefaultRoleOfServer(Server server) {
        DefaultRole defaultRole;

        defaultRole = defaultRoleService.findByServerId(server.getId());

        return defaultRole;
    }

}
