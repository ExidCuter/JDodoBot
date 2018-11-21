package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_admin")
public class Admin extends Identificator {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User m_user;

	@ManyToOne
	@JoinColumn(name = "server_id")
	private Server m_server;

	public User getUser()
	{
		return m_user;
	}

	public void setUser(User p_user)
	{
		m_user = p_user;
	}

	public Server getServer()
	{
		return m_server;
	}

	public void setServer(Server p_server)
	{
		m_server = p_server;
	}
}
