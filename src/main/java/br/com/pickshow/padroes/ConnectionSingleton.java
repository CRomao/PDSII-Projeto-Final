package br.com.pickshow.padroes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para fornecer a conexão com o banco de dados, onde implementa-se o
 * padrão de projeto Singleton.
 * 
 * @author Cicero Romão
 * 
 */
public class ConnectionSingleton {

	private static Connection con = null;
	private static String url = "jdbc:postgresql://ec2-184-72-236-57.compute-1.amazonaws.com:5432/d9mp55j7o59vg7";
	private static String user = "kibugfzzocvuku";
	private static String pass = "5cef6fc42b34cd1a34d23b87c510cf43b21d50b31868b1e7ba883638cbfc94b7";

	/**
	 * Método construtor vázio, para que não permita a instância dessa classe.
	 * 
	 */
	private ConnectionSingleton() {
	}

	/**
	 * Método para verificar se a variavel estática 'con' está nula. Se estiver,
	 * chama-se uma conexão do DriverManager.getConnection(). Se não estiver,
	 * retorna a própria variável 'con'. Isso permite que a classe tenha apenas uma
	 * intância sendo executada.
	 * 
	 * @author Cicero Romão
	 * @return Connection - A conexão com o banco.
	 */
	public static Connection getConexao() {
		if (con == null) {
			try {
				con = (Connection) DriverManager.getConnection(getUrl(), getUser(), getPass());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPass() {
		return pass;
	}

}
