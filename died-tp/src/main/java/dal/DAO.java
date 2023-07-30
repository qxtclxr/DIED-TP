package dal;

import java.sql.*;

public interface DAO<T> {
	public void insert(T obj) throws SQLException;
	
	public void update(T obj) throws SQLException;
	
	public void delete(T obj) throws SQLException;
	
	public T getByID(Integer id) throws SQLException, ClassNotFoundException;
}