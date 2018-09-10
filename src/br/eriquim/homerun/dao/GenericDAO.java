package br.eriquim.homerun.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PreDestroy;

public abstract class GenericDAO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5550185214144407664L;
	private Connection connection;
	
	public GenericDAO() {
		connection = Conexao.getConnection();
	}
	
	public abstract List<T> getAll() throws SQLException;
	public abstract void save(T t) throws SQLException;
	public abstract void update(T t) throws SQLException;
	public abstract void remove(T t)  throws SQLException;
	public abstract T findByPrimaryKey(Integer id) throws SQLException;

	public Connection getConnection() {
		try {
			if(connection ==null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@PreDestroy
	public void close() {
		try {
			if(connection !=null && !connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
