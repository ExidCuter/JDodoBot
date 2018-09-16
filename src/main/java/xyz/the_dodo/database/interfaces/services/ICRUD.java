package xyz.the_dodo.database.interfaces.services;

import java.util.List;

public interface ICRUD<T>
{
	T findById(long id);
	List<T> findAll();

	T save(T object);

	boolean delete(T object);
}
