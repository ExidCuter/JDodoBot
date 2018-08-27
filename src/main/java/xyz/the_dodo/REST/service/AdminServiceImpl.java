package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IAdminoRepo;
import xyz.the_dodo.database.interfaces.repos.IServerRepo;
import xyz.the_dodo.database.interfaces.repos.IUserRepo;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements IAdminService
{
	@Autowired
	private IAdminoRepo m_adminRepo;

	@Autowired
	private IUserRepo m_userRepo;

	@Autowired
	private IServerRepo m_serverRepo;

	public void setUserRepo(IUserRepo p_userRepo)
	{
		m_userRepo = p_userRepo;
	}

	public void setServerRepo(IServerRepo p_serverRepo)
	{
		m_serverRepo = p_serverRepo;
	}

	public void setAdminRepo(IAdminoRepo p_adminRepo)
	{
		m_adminRepo = p_adminRepo;
	}

	@Override
	public Admin findById(long id)
	{
		return m_adminRepo.getOne(id);
	}

	@Override
	public List<Admin> findAll()
	{
		return m_adminRepo.findAll();
	}

	@Override
	public List<Admin> getAdminsByServerId(String discordId)
	{
		List<Admin> admins;

		admins = m_adminRepo.findAll();

		admins.stream().filter(p_admin -> p_admin.getServer().getDiscordId().equals(discordId)).collect(Collectors.toList());

		return admins;
	}

	@Override
	public Admin save(Admin object)
	{
		if(object != null){
			return m_adminRepo.save(object);
		}

		return null;
	}

	@Override
	public Admin save(Admin oldObject, Admin newObject)
	{
		if(oldObject != null && newObject != null && newObject.getId().equals(oldObject.getId())){
			return m_adminRepo.save(newObject);
		}

		return null;
	}

	@Override
	public boolean delete(Admin object)
	{
		if(object != null) {
			m_adminRepo.deleteById(object.getId());
			return true;
		}

		return false;
	}
}
