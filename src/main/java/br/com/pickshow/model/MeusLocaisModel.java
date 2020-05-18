package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.pickshow.controller.MeusLocaisController;
import br.com.pickshow.padroes.ConnectionSingleton;
import br.com.pickshow.view.Local;

public class MeusLocaisModel {
	
	ArrayList<MeusLocaisController.Local> locais = new ArrayList<>();
	
	public ArrayList<MeusLocaisController.Local> conectar(int id_profissional) {
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT l.id, l.nomeLocal, l.ruaLocal, l.telefone, tl.tipo\r\n" + 
					"FROM AD_local l\r\n" + 
					"JOIN ad_tipoLocal tl ON l.id_tipoLocal = tl.id where l.id_profissional = "+id_profissional+ ";";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				locais.add(new MeusLocaisController.Local(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return locais;
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
