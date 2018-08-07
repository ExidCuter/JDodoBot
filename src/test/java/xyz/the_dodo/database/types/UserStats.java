package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;
import xyz.the_dodo.database.types.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_stats")
public class UserStats extends Identificator
{
	private long numOfMessages;
	private long numOfFiles;//mogoce mogoce ne
	private User user;


	public long getNumOfMessages() {
		return numOfMessages;
	}

	public void setNumOfMessages(long numOfMessages)
	{
		this.numOfMessages = numOfMessages;
	}

	public long getNumOfFiles()
	{
		return numOfFiles;
	}

	public void setNumOfFiles(long numOfFiles)
	{
		this.numOfFiles = numOfFiles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
