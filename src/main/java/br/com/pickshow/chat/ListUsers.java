package br.com.pickshow.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para a lista de usuários.
 * 
 * @author Cicero Romão
 * 
 */

public class ListUsers<T extends User> {

	public List<T> listUsers = new ArrayList<>();

	public void addNewUser(T newUser) {
		listUsers.add(newUser);
	}
}
