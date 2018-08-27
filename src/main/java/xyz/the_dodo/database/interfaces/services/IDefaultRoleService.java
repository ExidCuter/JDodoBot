package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.DefaultRole;

public interface IDefaultRoleService extends ICRUD<DefaultRole>
{
	DefaultRole findByServerId(Long serveriId);
}
