package br.com.pickshow.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.util.ArrayList;

/**
 * Classe para a lista de usuários.
 * 
 * @author Cicero Romão
 * 
 */

public class ListUsers {

	public ArrayList<User> listUsers = new ArrayList<>();

	public void addNewUser(User newUser) {
		listUsers.add(newUser);
	}
}
