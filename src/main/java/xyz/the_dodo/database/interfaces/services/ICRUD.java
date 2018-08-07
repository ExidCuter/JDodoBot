package xyz.the_dodo.database.interfaces.services;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ICRUD<T>
{
	T findById(long id);
	List<T> findAll();

	T save(T object);
	T save(T oldObject, T newObject);

	boolean delete(T object);
}
