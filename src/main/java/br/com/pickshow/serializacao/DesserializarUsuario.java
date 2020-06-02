package br.com.pickshow.serializacao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Classe para desserializar os dados login do usuário do 'arquivo.ser'.
 *
 * @author Cicero Romão
 * 
 */

public class DesserializarUsuario<T extends Usuario> {

	T usu = null;
	FileInputStream arquivo = null;

	/**
	 * Método construtor para receber o arquivo.
	 * 
	 * @author Cicero Romão
	 * @param arq String - arquivo para ser desserializado.
	 */
	public DesserializarUsuario(String arq) {
		try {
			this.arquivo = new FileInputStream(arq);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para a desserialização dos dados do usuário, onde lê os dados e
	 * atribui a variável usu.
	 * 
	 * @author Cicero Romão
	 */
	@SuppressWarnings("unchecked")
	public void desserializar() {
		try {
			ObjectInputStream entrada = new ObjectInputStream(arquivo);
			this.usu = (T) entrada.readObject();
			entrada.close();
			arquivo.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para retornar a lista depois de desserializada.
	 * 
	 * @return Usuario - Usuário desserializado.
	 */
	public T getLista() {
		return usu;
	}

}
