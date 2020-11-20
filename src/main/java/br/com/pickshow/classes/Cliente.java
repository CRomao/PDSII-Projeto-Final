package br.com.pickshow.classes;

import br.com.pickshow.model.CadastrarClienteModel;

public class Cliente {
	
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpf;
	
	
	/*public Cliente(String nome, String sobrenome, String email, String senha, String cpf) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
	}*/
	
	public int verificarCamposNulos(String nome, String sobrenome, String email, String senha, String cpf) {
		if(nome == null) {
			return 1;
		}else if(sobrenome == null) {
			return 1;
		}else if(email == null) {
			return 1;
		}else if(senha == null) {
			return 1;
		}else if(cpf == null) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int verificarCamposVazios(String nome, String sobrenome, String email, String senha, String cpf) {
		if(nome.equals("")) {
			return 1;
		}else if(sobrenome.equals("")) {
			return 1;
		}else if(email.equals("")) {
			return 1;
		}else if(senha.equals("")) {
			return 1;
		}else if(cpf.equals("")) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int camposInvalidos(String nome, String sobrenome, String email, String senha, String cpf) {
		if(nome.length() <= 3) {
			return 1;
		}else if(sobrenome.length() <= 3) {
			return 1;
		}else if(email.length() <= 3) {
			return 1;
		}else if(senha.length() <= 3) {
			return 1;
		}else if(cpf.length() <= 3) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public String cadastroCliente(String nome, String sobrenome, String email, String senha, String cpf) {
		String resultado = CadastrarClienteModel.insert(nome, sobrenome, email, senha, cpf);
		return resultado;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

}
