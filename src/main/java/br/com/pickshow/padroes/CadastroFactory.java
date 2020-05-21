package br.com.pickshow.padroes;

import java.io.IOException;

import br.com.pickshow.view.CadastrarClienteView;
import br.com.pickshow.view.CadastrarProfissionalView;
import javafx.stage.Stage;

/**
 * Classe para encapsular a escolha da classe concreta de cadastro, entre
 * profissional e cliente, onde implementa-se o padrão de projeto Factory Method.
 * 
 * @see A classe EscolherCadastroController.java, é nela que a CadastroFactory
 *      será chamada, passando qual cadastro desejado para criação.
 * @author Cicero Romão
 * 
 */

public class CadastroFactory {

	public static final int PROFISSIONAL = 1;
	public static final int CLIENTE = 2;

	/**
	 * Método para verificar qual o cadastro a ser criado de acordo com o parâmetro
	 * passado.
	 * 
	 * @author Cicero Romão
	 * @param tipoCadastro int - valor do tipo do cadastro.
	 * @return void - Sem retorno.
	 */

	public void criarCadastro(int tipoCadastro) throws IOException {

		switch (tipoCadastro) {
		case CadastroFactory.PROFISSIONAL:
			new CadastrarProfissionalView().start(new Stage());
			break;
		case CadastroFactory.CLIENTE:
			new CadastrarClienteView().start(new Stage());
			break;
		}

	}

}
