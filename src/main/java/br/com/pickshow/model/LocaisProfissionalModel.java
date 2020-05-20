package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.pickshow.controller.LocaisProfissionalController;
import br.com.pickshow.padroes.ConnectionSingleton;
import br.com.pickshow.view.VisualizarLocaisView;

public class LocaisProfissionalModel {
	
	static ArrayList<LocaisProfissionalController.Local> locais = new ArrayList<>();
	
	public static ArrayList<LocaisProfissionalController.Local> conectar(int id_profissional) {
		locais.clear();
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT l.id, l.nomeLocal, l.ruaLocal, l.telefone, tl.tipo\r\n" + 
					"FROM AD_local l\r\n" + 
					"JOIN ad_tipoLocal tl ON l.id_tipoLocal = tl.id where l.id_profissional = "+id_profissional+ " ORDER BY l.nomeLocal;";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				locais.add(new LocaisProfissionalController.Local(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return locais;
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String deletarRegistroLocal(int idLoal) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "DELETE FROM AD_local WHERE id = "+ idLoal;

			ps = conn.prepareStatement(sql);
			ps.execute();
			
			return "Deletado com sucesso!";
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
