package test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.pickshow.classes.Local;

public class CadastrarLocalTest {

	Local local = new Local();

	@Test
	@DisplayName("Teste para verificar se tem campos nulos.")
	void testarCamposNulos() {
		assertEquals(1, local.verificarCamposNulos(null, null, null, null, -1, -1));
	}

	@Test
	@DisplayName("Teste para verificar se tem campos vazios.")
	void testarCamposComValoresVazios() {
		assertEquals(1, local.verificarCamposVazios("", "", "", "", -1, -1));
	}

	@Test
	@DisplayName("Teste para verificar se tem campos com valores curtos.")
	void testarCamposComValoresInvalidos() {
		assertEquals(1, local.camposInvalidos("aaa", "bbb", "ccc", "ddd", -1, -1));
	}

	@Test
	@DisplayName("Teste para verificar se o cadastro é realizado com sucesso.")
	void testarCadastroLocal() {
		assertEquals("Cadastro realizado com sucesso!",
				local.cadastroLocal("Espaço Mix", "Rua das Flores", "88888888", "Tem Piscina", 1, 1));
	}

}
