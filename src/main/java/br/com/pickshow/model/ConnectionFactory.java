package br.com.pickshow.model;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

public class ConnectionFactory {
	
	public Connection getConnection() throws Exception {
		
		String url = "jdbc:postgresql://labsql.fapce.edu.br:3024/fap_2020_1";
		try {
			return DriverManager.getConnection(url, "obd_2018210009", "");
		} catch (Exception e) {
			 throw new Exception(e);
		}
	}

}
