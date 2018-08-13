package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;

public class Subscription extends Identificator {
	public Subscription()
	{
	}

	public Subscription(Long id)
	{
		super(id);
	}
}
