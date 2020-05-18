package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.pickshow.padroes.ConnectionSingleton;

public class CadastroMeuLocalModel {
	
	public static String conectar(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes, int areaLocal) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_local (nome, sobrenome, senha, email, cpf) "
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
