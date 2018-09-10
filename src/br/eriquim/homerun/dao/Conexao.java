package br.eriquim.homerun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
	      } catch(ClassNotFoundException e) {
	         System.out.println("Class not found "+ e);
	      }
		try {
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eriquim", "eriquim", "");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
