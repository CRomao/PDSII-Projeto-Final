package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pickshow.padroes.ConnectionSingleton;

/**
 * Classe model para o profissional cadastrar o seu local, onde será feita a
 * operação de inserção de um novo local no banco de dados.
 * 
 * @see A classe CadastrarLocaisController.java, é nela que o método será
 *      chamado.
 * @author Cicero Romão
 * 
 */

public class CadastrarLocaisModel {

	/**
	 * Método estático para realizar a inserção do local no banco de dados, onde
	 * pega a conexão com o banco, prepara o SQL de inserção e em seguida executa o
	 * comando.
	 * 
	 * @author Cicero Romão
	 * @param nomeLocal       String - Nome do local.
	 * @param ruaLocal        String - Endereço do local.
	 * @param telefoneContato String - Telefone do local.
	 * @param areaInformacoes String - Informações sobre o local.
	 * @param tipoAreaLocal   int - ID do tipo de Local(referente a tabela
	 *                        AD_tipoLocal).
	 * @param idProfissional  int - ID do profissional(referente a tabela
	 *                        AD_profissional).
	 * @return String - Mensagem informando se o cadastro foi realizado ou não.
	 */
	public static String insert(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idProfissional) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_local (id_profissional, nomeLocal, ruaLocal, telefone, informacoesLocal, id_tipoLocal) "
					+ "values(" + idProfissional + ", '" + nomeLocal + "', '" + ruaLocal + "', '" + telefoneContato
					+ "', '" + areaInformacoes + "'," + " " + tipoAreaLocal + ");";

			ps = conn.prepareStatement(sql);
			ps.execute();
			ps.close();
			return "Cadastro realizado com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}

	/**
	 * Método estático para realizar a atualização(UPDATE) do local no banco de
	 * dados, onde pega a conexão com o banco, prepara o SQL de atualizar(UPDATE) e
	 * em seguida executa o comando.
	 * 
	 * @author Cicero Romão
	 * @param nomeLocal       String - Nome do local.
	 * @param ruaLocal        String - Endereço do local.
	 * @param telefoneContato String - Telefone do local.
	 * @param areaInformacoes String - Informações sobre o local.
	 * @param tipoAreaLocal   int - ID do tipo de Local(referente a tabela
	 *                        AD_tipoLocal).
	 * @param idLoal          int - ID do local.
	 * @return String - Mensagem informando se a alteração foi realizada ou não.
	 */
	public static String update(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idLoal) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "UPDATE AD_local SET nomeLocal = '" + nomeLocal + "', ruaLocal = '" + ruaLocal
					+ "', telefone = '" + telefoneContato + "', " + "informacoesLocal = '" + areaInformacoes
					+ "', id_tipoLocal = '" + tipoAreaLocal + "' WHERE id = " + idLoal + ";";

			ps = conn.prepareStatement(sql);
			ps.execute();
			ps.close();
			return "Alterado com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}

	/**
	 * Método estático para poder pegar as informações do local, quando vai ser
	 * feito a alteração do local, onde pega a conexão com o banco, prepara o SQL de
	 * seleção(SELECT) e em seguida executa o comando, fazendo em seguida um while
	 * para pegar a informação do local e retornar ela.
	 * 
	 * @author Cicero Romão
	 * @param idLoal int - ID do local.
	 * @return String - Retorna a informação do local selecionado para alteração.
	 */
	public static String selectInformacoesLocal(int idLocal) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT informacoesLocal FROM AD_Local WHERE id = " + idLocal + ";";
			String inf = "";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				inf = rs.getString(1);
			}
			ps.close();
			rs.close();
			return inf;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
