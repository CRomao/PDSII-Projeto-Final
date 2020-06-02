package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.pickshow.controller.VisualizarLocaisController;
import br.com.pickshow.padroes.ConnectionSingleton;

/**
 * Classe model para pegar TODOS os locais cadastrados no sistema, onde será
 * feita uma seleção na view VW_LOCAL_LOCAIS.
 * 
 * @author Cicero Romão
 * 
 */
public class VisualizarLocaisModel {

	static ArrayList<VisualizarLocaisController.Local> locais = new ArrayList<>();

	/**
	 * Método estático para poder pegar os dados de todos os locais,para que o
	 * cliente veja, onde pega a conexão com o banco, prepara o SQL de
	 * seleção(SELECT) na view e em seguida executa o comando, fazendo em seguida um
	 * while para pegar os dados dos locais e armazenar na variável ArrayList
	 * locais. Em seguida retorna os locais.
	 * 
	 * @author Cicero Romão
	 * @return ArrayList - Retorna o ArrayList com todos os locais.
	 */
	public static ArrayList<VisualizarLocaisController.Local> mostrarLocaisCadastrados() {
		locais.clear();
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT * FROM VW_LOCAL_LOCAIS;";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				locais.add(new VisualizarLocaisController.Local(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5)));
			}
			ps.close();
			rs.close();
			return locais;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
