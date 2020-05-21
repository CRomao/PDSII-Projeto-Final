package br.com.pickshow.serializacao;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//Classe para serializar a lista de usuários.
public class SerializarListaUsuario {

	Usuario usu;

	// Construtor que recebe a lista.
	public SerializarListaUsuario(Usuario usu) {
		this.usu = usu;
	}

	// Função para a serialização.
	public void serializar() {
		try {
			// Cria o arquivo, joga no OutputStream e escreve no arquivo.
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
