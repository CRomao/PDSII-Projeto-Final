package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.pickshow.padroes.ConnectionSingleton;

/**
 * Classe model para cadastrar cliente, onde será feita a operação de inserção
 * de um novo cliente no banco de dados.
 * 
 * 
 * @author Cicero Romão
 * 
 */

public class CadastrarClienteModel {

	/**
	 * Método estático para realizar a inserção do cliente no banco de dados, onde
	 * pega a conexão com o banco, prepara o SQL de inserção e em seguida executa o
	 * comando.
	 * 
	 * @author Cicero Romão
	 * @param nome      String - Nome do cliente.
	 * @param sobrenome String - Sobrenome do cliente.
	 * @param email     String - Email do cliente.
	 * @param senha     String - Senha do cliente.
	 * @param cpf       String - CPF do cliente.
	 * @return String - Mensagem informando se o cadastro foi realizado ou não.
	 */
	public static String insert(String nome, String sobrenome, String email, String senha, String cpf) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "INSERT INTO AD_cliente (nome, sobrenome, senha, email, cpf) " + "values(?,?,?,?,?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, sobrenome);
			ps.setString(3, senha);
			ps.setString(4, email);
			ps.setString(5, cpf);
			
			ps.execute();
			ps.close();
			return "Cadastro realizado com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}
}
