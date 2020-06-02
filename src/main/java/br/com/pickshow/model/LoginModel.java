package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pickshow.padroes.ConnectionSingleton;

/**
 * Classe model para realizar o login do usuário.
 * 
 * @author Cicero Romão
 * 
 */
public class LoginModel {

	private static String nomeUser;
	private static int idUser;

	/**
	 * Método estático para fazer o login do usuário, quando ele clicar em 'entrar',
	 * será chamada essa função, onde pega a conexão com o banco, prepara o SQL de
	 * seleção(SELECT) - dependendo do tipo do usuário, ele acessa uma tabela
	 * diferente - e em seguida executa o comando. Após isso, aproveita e pega o ID
	 * do usuário e o nome dele, para poder ser utilizado na tela da Home.
	 * 
	 * @author Cicero Romão
	 * @param tipoUsuario int - Tipo do usuário.
	 * @param email       String - Email do usuário.
	 * @param senha       String - Senha do usuário.
	 * @return String - Mensagem informando se o login foi realizado ou não.
	 */
	public static String fazerLogin(int tipoUsuario, String email, String senha) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "";
			boolean login = false;
			String resultado = "";
			if (tipoUsuario == 0) {// profissional
				sql = "SELECT * FROM AD_profissional where email = ? AND senha = ?;";
			} else if (tipoUsuario == 1) {// cliente
				sql = "SELECT * FROM AD_cliente where email = ? AND senha = ?;";
			}

			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			

			if(rs.next()) {
				login = true;
				idUser = rs.getInt(1);
				nomeUser = rs.getString(2);
			}
			
			ps.close();
			rs.close();

			if (login) {
				resultado = "Login realizado com sucesso!";

			} else {
				resultado = "Email ou senha inválidos.";
			}

			return resultado;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco não estabelecida.";
	}

	/**
	 * Método estático para retornar o nome do usuário.
	 * 
	 * @return String - Nome do usuário.
	 */
	public static String pegarNomeUsuario() {
		return nomeUser;
	}

	/**
	 * Método estático para retornar o ID do usuário.
	 * 
	 * @return int - ID do usuário.
	 */
	public static int pegarIdusuario() {
		return idUser;
	}
}
