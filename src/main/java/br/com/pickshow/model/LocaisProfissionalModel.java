package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.pickshow.controller.LocaisProfissionalController;
import br.com.pickshow.padroes.ConnectionSingleton;
import br.com.pickshow.view.VisualizarLocaisView;

/**
 * Classe model para pegar os locais cadastrados do profissional, onde será
 * feita uma seleção dos locais.
 * 
 * @author Cicero Romão
 * 
 */
public class LocaisProfissionalModel {

	static ArrayList<LocaisProfissionalController.Local> locais = new ArrayList<>();

	/**
	 * Método estático para poder pegar os dados do local, quando vai ser feito a
	 * visualização dos locais do profissional, onde pega a conexão com o banco,
	 * prepara o SQL de seleção(SELECT) e em seguida executa o comando, fazendo em
	 * seguida um while para pegar os dados dos locais e armazenar na variável
	 * locais, pois vai ser selecionado todos os locais daquele profissional, armazenado
	 * em um ArrayList, e em seguida retorna os locais.
	 * 
	 * @author Cicero Romão
	 * @param id_profissional int - ID do profissional.
	 * @return ArrayList<LocaisProfissionalController.Local> - Retorna o ArrayList
	 *         dos locais do profissional.
	 */
	public static ArrayList<LocaisProfissionalController.Local> selecionarLocais(int id_profissional) {
		locais.clear();
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT l.id, l.nomeLocal, l.ruaLocal, l.telefone, tl.tipo\r\n" + "FROM AD_local l\r\n"
					+ "JOIN ad_tipoLocal tl ON l.id_tipoLocal = tl.id where l.id_profissional = " + id_profissional
					+ " ORDER BY l.nomeLocal;";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				locais.add(new LocaisProfissionalController.Local(rs.getInt(1), rs.getString(2), rs.getString(3),
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

	/**
	 * Método estático para poder realizar a remoção(DELETE) do local no banco de
	 * dados, onde pega a conexão com o banco, prepara o SQL de remoção(DELETE) e em
	 * seguida executa o comando.
	 * 
	 * @author Cicero Romão
	 * @param idLoal int - ID do local.
	 * @return String - Mensagem informando se a exclusão foi realizada ou não.
	 */
	public static String deletarRegistroLocal(int idLoal) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "DELETE FROM AD_local WHERE id = " + idLoal;

			ps = conn.prepareStatement(sql);
			ps.execute();
			ps.close();
			return "Excluído com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Erro ao excluir o registro!";
	}
}
