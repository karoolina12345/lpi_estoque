package com.fundatec.lpi.estoque.dao;

import java.io.IOException;
import java.util.List;

public interface BaseDAO<T> {
	public void save(T objeto) throws IOException;

	public void update(T objeto) throws IOException;

	public void delete(int id) throws IOException;

	public List<T> listAll() throws IOException;
}
