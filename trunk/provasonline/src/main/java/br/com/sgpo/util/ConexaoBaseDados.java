package br.com.sgpo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * M�todo respons�vel pela cria��o de conex�o com a base de dados.
 * 
 * @author Roseli
 * 
 */
public class ConexaoBaseDados {

	private static final String DATA_BASE_URL = "jdbc:postgresql://localhost:5432/sgpodb";
	private static final String DATA_BASE_USER = "postgres";
	private static final String DATA_BASE_PASSWORD = "Fabiano2014*";

	/**
	 * M�todo retorna objeto <b>connection</b> de conex�o com a base de dados.
	 * @return connection
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConexaoInstance() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(DATA_BASE_URL,
				DATA_BASE_USER, DATA_BASE_PASSWORD);
		return conn;
	}
}