package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.pickshow.padroes.ConnectionSingleton;

public class CadastrarProfissionalModel {
	
	public static String conectar(String nome, String sobrenome, String email, String senha, String cpfCnpj, int areaAtuacao) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_profissional (nome, sobrenome, senha, email, areaAtuacao, cpfCnpj) "
					+ "values('"+nome+"', '"+ sobrenome+ "', '"+senha+"', '"+email+"', "+areaAtuacao+", '"+cpfCnpj+"');";

			ps = conn.prepareStatement(sql);
			ps.execute();
			//conn.close();
			return "Cadastro realizado com sucesso!";
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}
}
