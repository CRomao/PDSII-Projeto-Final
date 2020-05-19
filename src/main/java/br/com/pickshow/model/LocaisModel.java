package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.pickshow.controller.LocaisController;
import br.com.pickshow.padroes.ConnectionSingleton;
import br.com.pickshow.view.Locais;

public class LocaisModel {
	
	static ArrayList<LocaisController.Local> locais = new ArrayList<>();
	
	public static ArrayList<LocaisController.Local> conectar() {
		locais.clear();
		try {
			Connection conn =  ConnectionSingleton.getConexao();
			PreparedStatement ps;

			String sql = "SELECT * FROM VW_LOCAL_LOCAIS;";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				locais.add(new LocaisController.Local(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return locais;
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
