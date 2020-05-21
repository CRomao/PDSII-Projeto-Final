package br.com.pickshow.serializacao;

import java.io.Serializable;

//Classe para o cadastro dos usuários.
public class Usuario implements Serializable {

	private String nome;
	private String senha;
	private boolean conectado;

	// Construtor da classe.
	public Usuario(String nome, String senha, boolean conectado) {
		setNome(nome);
		setSenha(senha);
		setConectado(conectado);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}


}
