package xyz.the_dodo.database.interfaces.services;

import xyz.the_dodo.database.types.Admin;

import java.util.List;

public interface IAdminService extends ICRUD<Admin>
{
	List<Admin> getAdminsByServerId(String discordId);
}
