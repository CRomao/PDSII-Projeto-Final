package test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.pickshow.classes.Profissional;

public class CadastrarProfissionalTest{

	Profissional profissional = new Profissional();
	
	//CADASTRO DE PROFISSIONAL
	@Test
	@DisplayName("Teste para verificar se tem campos nulos.")
	void testarCamposNulos() {
		assertEquals(0, profissional.verificarCamposNulos(null, null, null, null, null,-1));
	}
	
	@Test
	@DisplayName("Teste para verificar se tem campos vazios.")
	void testarCamposComValoresVazios() {
		assertEquals(0, profissional.verificarCamposVazios("", "", "", "", "",-1));
	}
	
	@Test
	@DisplayName("Teste para verificar se tem campos com valores curtos.")
	void testarCamposComValoresInvalidos() {
		assertEquals(0, profissional.camposInvalidos("aaa", "bbb", "ccc", "ddd", "1",-1));
	}
	
	@Test
	@DisplayName("Teste para verificar se o cadastro é realizado com sucesso.")
	void testarCadastroProfissional() {
		assertEquals(0, profissional.camposInvalidos("aaa", "bbb", "ccc", "ddd", "1",-1));
	}
	
	//LOGIN
	@Test
	@DisplayName("Teste para verificar os dados passados para o login estão no banco de dados. Se tiver, retorna a mensagem")
	void testarCamposLogin() {
		assertEquals("Login realizado com sucesso!", profissional.login("a", "a", 1));
	}
	
	@Test
	@DisplayName("Teste para verificar os dados passados para o login estão válidos.")
	void testarCamposLoginValoresInvalidos() {
		assertEquals(0, profissional.login("a", "a", 1));
	}
	
	@Test
	@DisplayName("Teste para verificar os dados passados para o login estão nulos.")
	void testarCamposLoginValoresNulos() {
		assertEquals(0, profissional.login(null, null, -1));
	}
	
	


}