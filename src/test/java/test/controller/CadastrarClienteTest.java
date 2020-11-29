package test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.pickshow.classes.Cliente;

public class CadastrarClienteTest {

	Cliente cliente = new Cliente();

	@Test
	@DisplayName("Teste para verificar se tem campos nulos.")
	void testarCamposNulos() {
		assertEquals(1, cliente.verificarCamposNulos(null, null, null, null, null));
	}

	@Test
	@DisplayName("Teste para verificar se tem campos vazios.")
	void testarCamposComValoresVazios() {
		assertEquals(1, cliente.verificarCamposVazios("", "", "", "", ""));
	}

	@Test
	@DisplayName("Teste para verificar se tem campos com valores curtos.")
	void testarCamposComValoresInvalidos() {
		assertEquals(1, cliente.camposInvalidos("aaa", "bbb", "ccc", "ddd", "1"));
	}

	@Test
	@DisplayName("Teste para verificar se o cadastro é realizado com sucesso.")
	void testarCadastroCliente() {
		assertEquals("Cadastro realizado com sucesso!",
				cliente.cadastroCliente("jose", "igor", "joseigor@gmail.com", "1234", "00000000000"));
	}

	// LOGIN
	@Test
	@DisplayName("Teste para verificar os dados passados para o login estão no banco de dados. Se tiver, retorna a mensagem")
	void testarCamposLogin() {
		assertEquals("Login realizado com sucesso!", cliente.login("a", "a", 1));
	}

	@Test
	@DisplayName("Teste para verificar os dados passados para o login estão válidos.")
	void testarCamposLoginValoresInvalidos() {
		assertEquals(0, cliente.loginCamposInvalidos("a", "a", 1));
	}

	@Test
	@DisplayName("Teste para verificar os dados passados para o login estão nulos.")
	void testarCamposLoginValoresNulos() {
		assertEquals(0, cliente.loginCamposNulos(null, null, -1));
	}

}