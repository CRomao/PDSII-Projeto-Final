package br.com.pickshow.serializacao;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Classe para serializar os dados do login do usuário. Serializando o email,
 * senha e o checkBox de continuar conectado.
 *
 * @author Cicero Romão
 * 
 */

public class SerializarUsuario<T extends Usuario> {

	T usu;

	// Construtor que recebe o usuário.
	public SerializarUsuario(T usu) {
		this.usu = usu;
	}

	/**
	 * Método para serializar os dados do usuário, onde cria-se o 'arquivo.ser' e em
	 * seguida escreve o usuáio logado nele - caso o usuário deseje permanecer
	 * conectado.
	 * 
	 */
	public void serializar() {
		try {
			FileOutputStream arquivo = new FileOutputStream("arquivo.ser");
			ObjectOutputStream saida = new ObjectOutputStream(arquivo);
			saida.writeObject(usu);
			saida.close();
			arquivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
