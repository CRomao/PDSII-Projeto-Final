package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pickshow.padroes.ConnectionSingleton;

public class CadastroMeusLocaisModel {

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
			return "Cadastro realizado com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}

	public static String update(String nomeLocal, String ruaLocal, String telefoneContato, String areaInformacoes,
			int tipoAreaLocal, int idProfissional) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "UPDATE AD_local SET nomeLocal = '" + nomeLocal + "', ruaLocal = '" + ruaLocal
					+ "', telefone = '" + telefoneContato + "', " + "informacoesLocal = '" + areaInformacoes
					+ "', id_tipoLocal = '" + tipoAreaLocal + "' WHERE id_profissional = " + idProfissional + ";";

			ps = conn.prepareStatement(sql);
			ps.execute();
			return "Alterado com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Conexão com o banco de dados perdida.";
	}

	public static int selectIdTipoLocal(String tipoLocal) {
		try {
			Connection conn = ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT id FROM AD_tipoLocal WHERE tipo ='" + tipoLocal + "';";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
