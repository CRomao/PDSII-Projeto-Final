package test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.pickshow.classes.Cliente;

public class CadastrarClienteTest{

	Cliente cliente = new Cliente();
	
	@Test
	@DisplayName("Teste para verificar se tem campos nulos.")
	void testarCamposNulos() {
		assertEquals(0, cliente.verificarCamposNulos(null, null, null, null, null));
	}
	
	@Test
	@DisplayName("Teste para verificar se tem campos vazios.")
	void testarCamposComValoresVazios() {
		assertEquals(0, cliente.verificarCamposVazios("", "", "", "", ""));
	}
	
	@Test
	@DisplayName("Teste para verificar se tem campos com valores curtos.")
	void testarCamposComValoresInvalidos() {
		assertEquals(0, cliente.camposInvalidos("aaa", "bbb", "ccc", "ddd", "1"));
	}


}