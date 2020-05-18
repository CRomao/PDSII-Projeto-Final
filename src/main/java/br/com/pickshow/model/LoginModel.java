package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pickshow.padroes.ConnectionSingleton;

public class LoginModel {
	
	private static String nomeUser;
	private static int idUser;
	
	public static String conectar(int tipoUsuario, String email, String senha) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "";

			if (tipoUsuario == 0) {//profissional
				sql = "SELECT * FROM AD_profissional where email = '" + email + "' AND senha = '"
						+ senha + "';";
			} else if (tipoUsuario == 1) {//cliente
				sql = "SELECT * FROM AD_cliente where email = '" + email + "' AND senha = '"
						+ senha + "';";
			}

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//conn.close();
				idUser = rs.getInt(1);
				nomeUser = rs.getString(2);
				return "Login realizado com sucesso!";
			} else {
				//conn.close();
				return "Email ou senha inválidos.";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Conexão com o banco não estabelecida.";
	}
	
	public static String pegarNomeUsuario() {
		return nomeUser;
	}
	
	public static int pegarIdusuario() {
		return idUser;
	}
}
