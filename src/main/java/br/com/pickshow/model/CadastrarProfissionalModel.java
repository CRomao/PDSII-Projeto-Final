package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.pickshow.padroes.ConnectionSingleton;

/**
 * Classe model para cadastrar profissional, onde será feita a operação de
 * inserção de um novo profissional no banco de dados.
 * 
 * @author Cicero Romão
 * 
 */
public class CadastrarProfissionalModel {

	/**
	 * Método estático para realizar a inserção do profissional no banco de dados,
	 * onde pega a conexão com o banco, prepara o SQL de inserção e em seguida
	 * executa o comando.
	 * 
	 * @author Cicero Romão
	 * @param nome        String - Nome do cliente.
	 * @param sobrenome   String - Sobrenome do cliente.
	 * @param email       String - Email do cliente.
	 * @param senha       String - Senha do cliente.
	 * @param cpfCnpj     String - CPF do cliente.
	 * @param areaAtuacao int - ID do comboBox da aréa de atuação.
	 * @return String - Mensagem informando se o cadastro foi realizado ou não.
	 */
	public static String insert(String nome, String sobrenome, String email, String senha, String cpfCnpj,
			int areaAtuacao) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_profissional (nome, sobrenome, senha, email, areaAtuacao, cpfCnpj) "
					+ "values('" + nome + "', '" + sobrenome + "', '" + senha + "', '" + email + "', " + areaAtuacao
					+ ", '" + cpfCnpj + "');";

			ps = conn.prepareStatement(sql);
			ps.execute();
			ps.close();
			return "Cadastro realizado com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}
}
