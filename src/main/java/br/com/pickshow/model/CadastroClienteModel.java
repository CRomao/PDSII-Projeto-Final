package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pickshow.padroes.ConnectionSingleton;

public class CadastroClienteModel {
	
	public static String conectar(String nome, String sobrenome, String email, String senha, String cpf) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_cliente (nome, sobrenome, senha, email, cpf) "
					+ "values('"+nome+"', '"+ sobrenome+ "', '"+senha+"', '"+email+"', '"+cpf+"');";

			ps = conn.prepareStatement(sql);
			ps.execute();
			return "Cadastro realizado com sucesso!";
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}
}
