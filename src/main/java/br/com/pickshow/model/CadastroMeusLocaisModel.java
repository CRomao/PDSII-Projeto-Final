package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.pickshow.padroes.ConnectionSingleton;

public class CadastroMeusLocaisModel {
	
	public static String conectar(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes, 
			int tipoAreaLocal, int idProfissional) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_local (id_profissional, nomeLocal, ruaLocal, telefone, informacoesLocal, id_tipoLocal) "
					+ "values("+idProfissional+", '"+nomeLocal+"', '"+ ruaLocal+ "', '"+telefoneContato+"', '"+areaInformacoes+"',"
							+ " "+tipoAreaLocal+");";

			ps = conn.prepareStatement(sql);
			ps.execute();
			return "Cadastro realizado com sucesso!";
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}
}
