package br.com.pickshow.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para a lista de usuários.
 * 
 * @author Cicero Romão
 * 
 */

public class ListUsers {

	public List<User> listUsers = new ArrayList<>();

	public void addNewUser(User newUser) {
		listUsers.add(newUser);
	}
}
